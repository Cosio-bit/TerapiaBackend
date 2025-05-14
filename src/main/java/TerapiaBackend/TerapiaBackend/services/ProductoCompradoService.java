package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProductoCompradoEntity;
import TerapiaBackend.TerapiaBackend.entities.ProductoEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProductoCompradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoCompradoService {

    @Autowired
    private ProductoCompradoRepository productoCompradoRepository;

    @Autowired
    private ProductoService productoService;

    public List<ProductoCompradoEntity> findAll() {
        return productoCompradoRepository.findAll();
    }

    public Optional<ProductoCompradoEntity> findById(Long id_producto_comprado) {
        return productoCompradoRepository.findById(id_producto_comprado);
    }

    public ProductoCompradoEntity save(ProductoCompradoEntity productoComprado) {
        ProductoEntity producto = productoService.findById(productoComprado.getProducto().getId_producto())
                .orElseThrow(() -> new RuntimeException("El producto no existe"));

        if (producto.getStock() < productoComprado.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para el producto con ID: " + producto.getId_producto());
        }

        producto.setStock(producto.getStock() - productoComprado.getCantidad());
        productoService.save(producto);

        return productoCompradoRepository.save(productoComprado);
    }

    public ProductoCompradoEntity update(Long id_producto_comprado, ProductoCompradoEntity updatedProductoComprado) {
        return productoCompradoRepository.findById(id_producto_comprado).map(productoComprado -> {

            ProductoEntity productoAnterior = productoComprado.getProducto();
            ProductoEntity productoNuevo = productoService.findById(updatedProductoComprado.getProducto().getId_producto())
                    .orElseThrow(() -> new RuntimeException("El producto nuevo no existe"));

            if (!productoAnterior.getId_producto().equals(productoNuevo.getId_producto())) {
                productoAnterior.setStock(productoAnterior.getStock() + productoComprado.getCantidad());
                productoService.save(productoAnterior);

                if (productoNuevo.getStock() < updatedProductoComprado.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para el producto nuevo con ID: " + productoNuevo.getId_producto());
                }

                productoNuevo.setStock(productoNuevo.getStock() - updatedProductoComprado.getCantidad());
                productoService.save(productoNuevo);
            } else {
                int diferencia = updatedProductoComprado.getCantidad() - productoComprado.getCantidad();
                if (productoNuevo.getStock() < diferencia) {
                    throw new RuntimeException("Stock insuficiente para ajustar cantidad del producto con ID: " + productoNuevo.getId_producto());
                }
                productoNuevo.setStock(productoNuevo.getStock() - diferencia);
                productoService.save(productoNuevo);
            }

            productoComprado.setProducto(updatedProductoComprado.getProducto());
            productoComprado.setCantidad(updatedProductoComprado.getCantidad());

            return productoCompradoRepository.save(productoComprado);
        }).orElseThrow(() -> new RuntimeException("Producto comprado no encontrado con ID: " + id_producto_comprado));
    }

    public void deleteById(Long id_producto_comprado) {
        ProductoCompradoEntity productoComprado = productoCompradoRepository.findById(id_producto_comprado)
                .orElseThrow(() -> new RuntimeException("Producto comprado no encontrado con ID: " + id_producto_comprado));

        ProductoEntity producto = productoComprado.getProducto();
        producto.setStock(producto.getStock() + productoComprado.getCantidad());
        productoService.save(producto);

        productoCompradoRepository.deleteById(id_producto_comprado);
    }

    public List<ProductoCompradoEntity> importarProductosComprados(List<ProductoCompradoEntity> productosComprados) {
        productosComprados.forEach(this::save);
        return productosComprados;
    }
}
