package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.CompraEntity;
import TerapiaBackend.TerapiaBackend.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<CompraEntity> findAll() {
        return compraRepository.findAll();
    }

    public Optional<CompraEntity> findById(Long id) {
        return compraRepository.findById(id);
    }

    public CompraEntity save(CompraEntity compra) {
        return compraRepository.save(compra);
    }

    public void deleteById(Long id) {
        compraRepository.deleteById(id);
    }

    public CompraEntity update(Long id, CompraEntity updatedCompra) {
        return compraRepository.findById(id).map(compra -> {
            compra.setFecha_compra(updatedCompra.getFecha_compra());
            compra.setTotal(updatedCompra.getTotal());
            compra.setId_cliente(updatedCompra.getId_cliente());
            return compraRepository.save(compra);
        }).orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
    }
}
