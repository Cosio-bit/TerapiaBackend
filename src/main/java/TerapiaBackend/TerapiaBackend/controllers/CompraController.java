package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.CompraEntity;
import TerapiaBackend.TerapiaBackend.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<CompraEntity>> obtenerTodas() {
        List<CompraEntity> compras = compraService.findAll();
        return compras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraEntity> obtenerPorId(@PathVariable Long id) {
        return compraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompraEntity> crear(@RequestBody Map<String, Object> requestBody) {
        CompraEntity compra = new CompraEntity();
        boolean usarSaldo = (boolean) requestBody.getOrDefault("usarSaldo", false);

        // Extract CompraEntity from request body
        if (requestBody.containsKey("compra")) {
            compra = (CompraEntity) requestBody.get("compra");
        }

        CompraEntity savedCompra = compraService.confirmarCompra(compra, usarSaldo);
        return ResponseEntity.ok(savedCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraEntity> actualizar(@PathVariable Long id, @RequestBody CompraEntity compra) {
        return compraService.update(id, compra)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (compraService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<CompraEntity>> crearEnLote(@RequestBody List<CompraEntity> compras) {
        if (compras.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<CompraEntity> savedCompras = compraService.saveAll(compras);
        return ResponseEntity.ok(savedCompras);
    }
}
