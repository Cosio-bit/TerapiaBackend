package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.CompraEntity;
import TerapiaBackend.TerapiaBackend.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<CompraEntity>> obtenerTodasLasCompras() {
        List<CompraEntity> compras = compraService.findAll();
        return compras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(compras);
    }

    @GetMapping("/{id_compra}")
    public ResponseEntity<CompraEntity> obtenerCompraPorId(@PathVariable Long id_compra) {
        return compraService.findById(id_compra)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> crearCompra(@RequestBody CompraEntity compra) {
        if (compra.getCliente() == null || compra.getProductosComprados() == null || compra.getProductosComprados().isEmpty()) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: cliente o productos comprados.");
        }
        return ResponseEntity.ok(compraService.save(compra));
    }

    @Transactional
    @PutMapping("/{id_compra}")
    public ResponseEntity<?> actualizarCompra(@PathVariable Long id_compra, @RequestBody CompraEntity compra) {
        if (compra.getCliente() == null || compra.getProductosComprados() == null || compra.getProductosComprados().isEmpty()) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: cliente o productos comprados.");
        }
        return ResponseEntity.ok(compraService.update(id_compra, compra));
    }

    @DeleteMapping("/{id_compra}")
    public ResponseEntity<?> eliminarCompra(@PathVariable Long id_compra) {
        try {
            compraService.deleteById(id_compra);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/importar")
    public ResponseEntity<List<CompraEntity>> importarCompras(@RequestBody List<CompraEntity> compras) {
        return ResponseEntity.ok(compraService.importarCompras(compras));
    }
}
