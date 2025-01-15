package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProveedorEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<ProveedorEntity> findAll() {
        return proveedorRepository.findAll();
    }

    public Optional<ProveedorEntity> findById(Long id) {
        return proveedorRepository.findById(id);
    }

    public ProveedorEntity save(ProveedorEntity proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void deleteById(Long id) {
        proveedorRepository.deleteById(id);
    }

    public ProveedorEntity update(Long id, ProveedorEntity updatedProveedor) {
        return proveedorRepository.findById(id).map(proveedor -> {
            proveedor.setId_usuario(updatedProveedor.getId_usuario());
            proveedor.setRut_empresa(updatedProveedor.getRut_empresa());
            proveedor.setDireccion(updatedProveedor.getDireccion());
            proveedor.setTelefono(updatedProveedor.getTelefono());
            proveedor.setEmail(updatedProveedor.getEmail());
            return proveedorRepository.save(proveedor);
        }).orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }
}
