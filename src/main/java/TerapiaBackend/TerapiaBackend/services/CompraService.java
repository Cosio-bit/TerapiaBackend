package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.CompraEntity;
import TerapiaBackend.TerapiaBackend.entities.ProductoCompradoEntity;
import TerapiaBackend.TerapiaBackend.entities.ProductoEntity;
import TerapiaBackend.TerapiaBackend.entities.ClienteEntity;
import TerapiaBackend.TerapiaBackend.repositories.CompraRepository;
import TerapiaBackend.TerapiaBackend.repositories.ProductoRepository;
import TerapiaBackend.TerapiaBackend.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public CompraEntity confirmarCompra(CompraEntity compra, boolean usarSaldo) {
        BigDecimal total = BigDecimal.ZERO;
        ClienteEntity cliente = compra.getCliente();

        for (ProductoCompradoEntity productoComprado : compra.getProductosComprados()) {
            ProductoEntity producto = productoRepository.findById(productoComprado.getProducto().getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productoComprado.getProducto().getId_producto()));

            if (producto.getStock() < productoComprado.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - productoComprado.getCantidad());
            productoRepository.save(producto);

            BigDecimal subtotal = BigDecimal.valueOf(producto.getPrecio() * productoComprado.getCantidad());
            total = total.add(subtotal);
        }

        // Check if the client wants to use saldo
        if (usarSaldo) {
            if (cliente.getSaldo() != null && cliente.getSaldo() >= total.doubleValue()) {
                cliente.setSaldo(cliente.getSaldo() - total.doubleValue());
                clienteRepository.save(cliente);
            } else {
                throw new RuntimeException("Saldo insuficiente para el cliente: " + cliente.getUsuario().getNombre());
            }
        }

        compra.setTotal(total);
        return compraRepository.save(compra);
    }

    public List<CompraEntity> findAll() {
        return compraRepository.findAll();
    }

    public Optional<CompraEntity> findById(Long id) {
        return compraRepository.findById(id);
    }

    public Optional<CompraEntity> update(Long id, CompraEntity updatedCompra) {
        return compraRepository.findById(id).map(compra -> {
            compra.setFecha(updatedCompra.getFecha());
            compra.setTotal(updatedCompra.getTotal());
            compra.setCliente(updatedCompra.getCliente());
            compra.setProductosComprados(updatedCompra.getProductosComprados());
            return compraRepository.save(compra);
        });
    }

    public boolean deleteById(Long id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CompraEntity> saveAll(List<CompraEntity> compras) {
        return compraRepository.saveAll(compras);
    }
}
