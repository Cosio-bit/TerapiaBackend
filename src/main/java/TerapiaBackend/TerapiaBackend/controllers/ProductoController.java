package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProductoEntity;
import TerapiaBackend.TerapiaBackend.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Fetch all productos.
     */
    @GetMapping
    public ResponseEntity<List<ProductoEntity>> obtenerTodosLosProductos() {
        List<ProductoEntity> productos = productoService.findAll();
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }

    /**
     * Fetch a single producto by ID.
     */
    @GetMapping("/{id_producto}")
    public ResponseEntity<ProductoEntity> obtenerProductoPorId(@PathVariable Long id_producto) {
        Optional<ProductoEntity> producto = productoService.findById(id_producto);
        return producto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new producto.
     */
    @Transactional
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody ProductoEntity producto) {
        if (producto.getProveedor() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: proveedor.");
        }
        return ResponseEntity.ok(productoService.save(producto));
    }

    /**
     * Update an existing producto.
     */
    @Transactional
    @PutMapping("/{id_producto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id_producto, @RequestBody ProductoEntity producto) {
        if (producto.getProveedor() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: proveedor.");
        }
        return ResponseEntity.ok(productoService.update(id_producto, producto));
    }

    /**
     * Delete a producto by ID.
     */
    @DeleteMapping("/{id_producto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id_producto) {
        try {
            productoService.deleteById(id_producto);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
