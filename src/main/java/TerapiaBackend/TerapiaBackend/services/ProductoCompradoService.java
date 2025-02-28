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

    // Obtener todos los productos comprados
    public List<ProductoCompradoEntity> findAll() {
        return productoCompradoRepository.findAll();
    }

    // Obtener un producto comprado por ID
    public Optional<ProductoCompradoEntity> findById(Long id) {
        return productoCompradoRepository.findById(id);
    }

    // Crear o actualizar un producto comprado
    public ProductoCompradoEntity save(ProductoCompradoEntity productoComprado) {
        return productoCompradoRepository.save(productoComprado);
    }

    // Eliminar un producto comprado por ID
    public boolean deleteById(Long id) {
        if (productoCompradoRepository.existsById(id)) {
            productoCompradoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar un producto comprado
    public Optional<ProductoCompradoEntity> update(Long id, ProductoCompradoEntity updatedProductoComprado) {
        return productoCompradoRepository.findById(id).map(productoComprado -> {
            actualizarProductoComprado(productoComprado, updatedProductoComprado);
            return productoCompradoRepository.save(productoComprado);
        });
    }

    private void actualizarProductoComprado(ProductoCompradoEntity existente, ProductoCompradoEntity actualizado) {
        existente.setNombre(actualizado.getNombre());
        existente.setPrecio(actualizado.getPrecio());
        existente.setCantidad(actualizado.getCantidad());
        existente.setProducto(actualizado.getProducto());
    }

    public List<ProductoCompradoEntity> saveAll(List<ProductoCompradoEntity> productosComprados) {
        return productoCompradoRepository.saveAll(productosComprados);
    }
}
