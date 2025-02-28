package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProveedorEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<ProveedorEntity> findAll() {
        return proveedorRepository.findAll();
    }

    public Optional<ProveedorEntity> findById(Long id_proveedor) {
        return proveedorRepository.findById(id_proveedor);
    }

    public ProveedorEntity save(ProveedorEntity proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public ProveedorEntity update(Long id_proveedor, ProveedorEntity updatedProveedor) {
        return proveedorRepository.findById(id_proveedor).map(proveedor -> {
            proveedor.setUsuario(updatedProveedor.getUsuario());
            proveedor.setRut_empresa(updatedProveedor.getRut_empresa());
            proveedor.setDireccion(updatedProveedor.getDireccion());
            proveedor.setTelefono(updatedProveedor.getTelefono());
            proveedor.setEmail(updatedProveedor.getEmail());
            return proveedorRepository.save(proveedor);
        }).orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id_proveedor));
    }

    public void deleteById(Long id_proveedor) {
        proveedorRepository.deleteById(id_proveedor);
    }

    // Guardar múltiples proveedores
    public List<ProveedorEntity> saveAll(List<ProveedorEntity> proveedores) {
        for (ProveedorEntity proveedor : proveedores) {
            if (proveedor.getUsuario() == null || proveedor.getUsuario().getId_usuario() == null) {
                throw new RuntimeException("Cada proveedor debe tener un usuario asociado.");
            }
        }
        return proveedorRepository.saveAll(proveedores);
    }
}
