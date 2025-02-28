package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProductoEntity;
import TerapiaBackend.TerapiaBackend.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoEntity>> getAllProductos() {
        List<ProductoEntity> productos = productoService.findAll();
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }

    @GetMapping("/{id_producto}")
    public ResponseEntity<ProductoEntity> getProductoById(@PathVariable Long id_producto) {
        return ResponseEntity.ok(productoService.findById(id_producto));
    }

    @PostMapping
    public ResponseEntity<ProductoEntity> createProducto(@RequestBody ProductoEntity producto) {
        return ResponseEntity.ok(productoService.save(producto));
    }

    @PutMapping("/{id_producto}")
    public ResponseEntity<ProductoEntity> updateProducto(@PathVariable Long id_producto, @RequestBody ProductoEntity producto) {
        return ResponseEntity.ok(productoService.update(id_producto, producto));
    }

    @DeleteMapping("/{id_producto}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id_producto) {
        productoService.deleteById(id_producto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<ProductoEntity>> crearEnLote(@RequestBody List<ProductoEntity> productos) {
        return ResponseEntity.ok(productoService.saveAll(productos));
    }
}
