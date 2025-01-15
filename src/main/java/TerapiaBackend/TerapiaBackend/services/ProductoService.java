package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProductoEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoEntity> findAll() {
        return productoRepository.findAll();
    }

    public Optional<ProductoEntity> findById(Long id) {
        return productoRepository.findById(id);
    }

    public ProductoEntity save(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public ProductoEntity update(Long id, ProductoEntity updatedProducto) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(updatedProducto.getNombre());
            producto.setFecha_creacion(updatedProducto.getFecha_creacion());
            producto.setPrecio(updatedProducto.getPrecio());
            producto.setStock(updatedProducto.getStock());
            producto.setId_proveedor(updatedProducto.getId_proveedor());
            producto.setId_categoria(updatedProducto.getId_categoria());
            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }
}
