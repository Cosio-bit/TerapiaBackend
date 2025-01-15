package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProductoCompradoEntity;
import TerapiaBackend.TerapiaBackend.services.ProductoCompradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productoscomprados")
public class ProductoCompradoController {

    @Autowired
    private ProductoCompradoService productoCompradoService;

    @GetMapping
    public List<ProductoCompradoEntity> getAllProductosComprados() {
        return productoCompradoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductoCompradoEntity> getProductoCompradoById(@PathVariable Long id) {
        return productoCompradoService.findById(id);
    }

    @PostMapping
    public ProductoCompradoEntity createProductoComprado(@RequestBody ProductoCompradoEntity productoComprado) {
        return productoCompradoService.save(productoComprado);
    }

    @PutMapping("/{id}")
    public ProductoCompradoEntity updateProductoComprado(@PathVariable Long id, @RequestBody ProductoCompradoEntity productoComprado) {
        return productoCompradoService.update(id, productoComprado);
    }

    @DeleteMapping("/{id}")
    public void deleteProductoComprado(@PathVariable Long id) {
        productoCompradoService.deleteById(id);
    }
}
