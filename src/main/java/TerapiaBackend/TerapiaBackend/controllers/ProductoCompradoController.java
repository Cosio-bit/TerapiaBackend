package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProductoCompradoEntity;
import TerapiaBackend.TerapiaBackend.services.ProductoCompradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-comprados")
public class ProductoCompradoController {

    @Autowired
    private ProductoCompradoService productoCompradoService;

    // Obtener todos los productos comprados
    @GetMapping
    public ResponseEntity<List<ProductoCompradoEntity>> obtenerTodos() {
        List<ProductoCompradoEntity> productosComprados = productoCompradoService.findAll();
        return productosComprados.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productosComprados);
    }

    // Obtener un producto comprado por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoCompradoEntity> obtenerPorId(@PathVariable Long id) {
        return productoCompradoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoCompradoEntity> crear(@RequestBody ProductoCompradoEntity productoComprado) {
        if (productoComprado.getProducto() == null || productoComprado.getCantidad() <= 0 || productoComprado.getPrecio() == null) {
            return ResponseEntity.badRequest().build();
        }
        ProductoCompradoEntity creado = productoCompradoService.save(productoComprado);
        return ResponseEntity.ok(creado);
    }


    // Actualizar un producto comprado
    @PutMapping("/{id}")
    public ResponseEntity<ProductoCompradoEntity> actualizar(
            @PathVariable Long id,
            @RequestBody ProductoCompradoEntity productoComprado) {
        return productoCompradoService.update(id, productoComprado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un producto comprado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productoCompradoService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<ProductoCompradoEntity>> crearEnLote(@RequestBody List<ProductoCompradoEntity> productosComprados) {
        return ResponseEntity.ok(productoCompradoService.saveAll(productosComprados));
    }
}
