package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProductoEntity;
import TerapiaBackend.TerapiaBackend.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoEntity> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductoEntity> getProductoById(@PathVariable Long id) {
        return productoService.findById(id);
    }

    @PostMapping
    public ProductoEntity createProducto(@RequestBody ProductoEntity producto) {
        return productoService.save(producto);
    }

    @PutMapping("/{id}")
    public ProductoEntity updateProducto(@PathVariable Long id, @RequestBody ProductoEntity producto) {
        return productoService.update(id, producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
    }
}
