package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.*;
import TerapiaBackend.TerapiaBackend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoCompradoRepository productoCompradoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<CompraEntity> findAll() {
        return compraRepository.findAll();
    }

    public Optional<CompraEntity> findById(Long id_compra) {
        return compraRepository.findById(id_compra);
    }

    @Transactional
    public CompraEntity save(CompraEntity compra) {
        // Ensure cliente exists
        ClienteEntity cliente = clienteRepository.findById(compra.getCliente().getId_cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found"));
        compra.setCliente(cliente);

        // ✅ Step 1: Save each product first so it gets an ID
        List<ProductoCompradoEntity> savedProductos = new ArrayList<>();
        for (ProductoCompradoEntity productoComprado : compra.getProductosComprados()) {
            productoComprado.setId_producto_comprado(null); // Ensure new purchase
            ProductoCompradoEntity savedProducto = productoCompradoRepository.save(productoComprado);
            savedProductos.add(savedProducto);
        }

        // ✅ Step 2: Attach saved products to the purchase
        compra.setProductosComprados(savedProductos);

        // ✅ Step 3: Save the purchase with the now-persisted products
        return compraRepository.save(compra);
    }

    @Transactional
    public CompraEntity update(Long id_compra, CompraEntity updatedCompra) {
        return compraRepository.findById(id_compra).map(compra -> {
            compra.setCliente(updatedCompra.getCliente());
            compra.setFecha(updatedCompra.getFecha());

            // ✅ Handle purchased products update
            compra.getProductosComprados().clear(); // Remove old products
            for (ProductoCompradoEntity productoComprado : updatedCompra.getProductosComprados()) {
                productoComprado.setId_producto_comprado(null); // Ensures new products get generated IDs
                compra.getProductosComprados().add(productoComprado);
            }

            return compraRepository.save(compra);
        }).orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id_compra));
    }

    @Transactional
    public void deleteById(Long id_compra) {
        if (!compraRepository.existsById(id_compra)) {
            throw new RuntimeException("Compra no encontrada con ID: " + id_compra);
        }
        compraRepository.deleteById(id_compra);
    }

    public List<CompraEntity> importarCompras(List<CompraEntity> compras) {
        return compraRepository.saveAll(compras);
    }



}
