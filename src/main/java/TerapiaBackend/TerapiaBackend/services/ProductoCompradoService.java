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

    public Optional<ProductoCompradoEntity> findById(Long id) {
        return productoCompradoRepository.findById(id);
    }

    public ProductoCompradoEntity save(ProductoCompradoEntity productoComprado) {
        return productoCompradoRepository.save(productoComprado);
    }

    public void deleteById(Long id) {
        productoCompradoRepository.deleteById(id);
    }

    public ProductoCompradoEntity update(Long id, ProductoCompradoEntity updatedProductoComprado) {
        return productoCompradoRepository.findById(id).map(productoComprado -> {
            productoComprado.setNombre(updatedProductoComprado.getNombre());
            productoComprado.setPrecio(updatedProductoComprado.getPrecio());
            productoComprado.setCantidad(updatedProductoComprado.getCantidad());
            productoComprado.setId_producto(updatedProductoComprado.getId_producto());
            productoComprado.setId_compra(updatedProductoComprado.getId_compra());
            return productoCompradoRepository.save(productoComprado);
        }).orElseThrow(() -> new RuntimeException("Producto comprado no encontrado con ID: " + id));
    }
}
