package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProductoEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoEntity> findAll() {
        return productoRepository.findAll();
    }

    public ProductoEntity findById(Long id_producto) {
        return productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id_producto));
    }

    public ProductoEntity save(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public ProductoEntity update(Long id_producto, ProductoEntity updatedProducto) {
        return productoRepository.findById(id_producto).map(producto -> {
            producto.setNombre(updatedProducto.getNombre());
            producto.setDescripcion(updatedProducto.getDescripcion());
            producto.setPrecio(updatedProducto.getPrecio());
            producto.setStock(updatedProducto.getStock());
            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id_producto));
    }

    public void deleteById(Long id_producto) {
        if (!productoRepository.existsById(id_producto)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id_producto);
        }
        productoRepository.deleteById(id_producto);
    }

    public List<ProductoEntity> saveAll(List<ProductoEntity> productos) {
        return productoRepository.saveAll(productos);
    }
}
