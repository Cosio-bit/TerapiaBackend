package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.*;
import TerapiaBackend.TerapiaBackend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    /**
     * Fetch all productos.
     */
    public List<ProductoEntity> findAll() {
        return productoRepository.findAll();
    }

    /**
     * Fetch a single producto by ID.
     */
    public Optional<ProductoEntity> findById(Long id_producto) {
        return productoRepository.findById(id_producto);
    }

    /**
     * Save a new producto, ensuring proveedor exists.
     */
    @Transactional
    public ProductoEntity save(ProductoEntity producto) {
        // ✅ Ensure `proveedor` exists before saving the producto
        ProveedorEntity proveedor = proveedorRepository.findById(producto.getProveedor().getId_proveedor())
                .orElseThrow(() -> new EntityNotFoundException("Proveedor not found"));

        producto.setProveedor(proveedor);
        return productoRepository.save(producto);
    }

    /**
     * Update an existing producto.
     */
    @Transactional
    public ProductoEntity update(Long id_producto, ProductoEntity updatedProducto) {
        return productoRepository.findById(id_producto).map(producto -> {
            ProveedorEntity proveedor = proveedorRepository.findById(updatedProducto.getProveedor().getId_proveedor())
                    .orElseThrow(() -> new EntityNotFoundException("Proveedor not found"));

            producto.setProveedor(proveedor);
            producto.setNombre(updatedProducto.getNombre());
            producto.setDescripcion(updatedProducto.getDescripcion());
            producto.setPrecio(updatedProducto.getPrecio());
            producto.setStock(updatedProducto.getStock());

            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id_producto));
    }

    /**
     * Delete a producto by ID.
     */
    @Transactional
    public void deleteById(Long id_producto) {
        if (!productoRepository.existsById(id_producto)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id_producto);
        }
        productoRepository.deleteById(id_producto);
    }
}
