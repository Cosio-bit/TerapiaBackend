package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProductoCompradoEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProductoCompradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoCompradoService {

    @Autowired
    private ProductoCompradoRepository productoCompradoRepository;

    public List<ProductoCompradoEntity> findAll() {
        return productoCompradoRepository.findAll();
    }

    public Optional<ProductoCompradoEntity> findById(Long id_producto_comprado) {
        return productoCompradoRepository.findById(id_producto_comprado);
    }

    public ProductoCompradoEntity save(ProductoCompradoEntity productoComprado) {
        return productoCompradoRepository.save(productoComprado);
    }

    public ProductoCompradoEntity update(Long id_producto_comprado, ProductoCompradoEntity updatedProductoComprado) {
        return productoCompradoRepository.findById(id_producto_comprado).map(productoComprado -> {
            productoComprado.setProducto(updatedProductoComprado.getProducto());
            productoComprado.setCantidad(updatedProductoComprado.getCantidad());
            return productoCompradoRepository.save(productoComprado);
        }).orElseThrow(() -> new RuntimeException("Producto comprado no encontrado con ID: " + id_producto_comprado));
    }

    public void deleteById(Long id_producto_comprado) {
        if (!productoCompradoRepository.existsById(id_producto_comprado)) {
            throw new RuntimeException("Producto comprado no encontrado con ID: " + id_producto_comprado);
        }
        productoCompradoRepository.deleteById(id_producto_comprado);
    }

    public List<ProductoCompradoEntity> importarProductosComprados(List<ProductoCompradoEntity> productosComprados) {
        return productoCompradoRepository.saveAll(productosComprados);
    }
}
