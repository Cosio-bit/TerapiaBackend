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

    @GetMapping
    public ResponseEntity<List<ProductoCompradoEntity>> obtenerTodosLosProductosComprados() {
        List<ProductoCompradoEntity> productosComprados = productoCompradoService.findAll();
        return productosComprados.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productosComprados);
    }

    @GetMapping("/{id_producto_comprado}")
    public ResponseEntity<ProductoCompradoEntity> obtenerProductoCompradoPorId(@PathVariable Long id_producto_comprado) {
        return productoCompradoService.findById(id_producto_comprado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoCompradoEntity> crearProductoComprado(@RequestBody ProductoCompradoEntity productoComprado) {
        return ResponseEntity.ok(productoCompradoService.save(productoComprado));
    }

    @PutMapping("/{id_producto_comprado}")
    public ResponseEntity<ProductoCompradoEntity> actualizarProductoComprado(@PathVariable Long id_producto_comprado, @RequestBody ProductoCompradoEntity productoComprado) {
        return ResponseEntity.ok(productoCompradoService.update(id_producto_comprado, productoComprado));
    }

    @DeleteMapping("/{id_producto_comprado}")
    public ResponseEntity<Void> eliminarProductoComprado(@PathVariable Long id_producto_comprado) {
        productoCompradoService.deleteById(id_producto_comprado);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<ProductoCompradoEntity>> importarProductosComprados(@RequestBody List<ProductoCompradoEntity> productosComprados) {
        return ResponseEntity.ok(productoCompradoService.importarProductosComprados(productosComprados));
    }
}
