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
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    /**
     * Fetch all salas.
     */
    public List<SalaEntity> findAll() {
        return salaRepository.findAll();
    }

    /**
     * Fetch a single sala by ID.
     */
    public Optional<SalaEntity> findById(Long id_sala) {
        return salaRepository.findById(id_sala);
    }

    /**
     * Save a new sala, ensuring proveedor exists.
     */
    @Transactional
    public SalaEntity save(SalaEntity sala) {
        // ✅ Ensure `proveedor` exists before saving the sala
        ProveedorEntity proveedor = proveedorRepository.findById(sala.getProveedor().getId_proveedor())
                .orElseThrow(() -> new EntityNotFoundException("Proveedor not found"));

        sala.setProveedor(proveedor);
        return salaRepository.save(sala);
    }

    /**
     * Update an existing sala.
     */
    @Transactional
    public SalaEntity update(Long id_sala, SalaEntity updatedSala) {
        return salaRepository.findById(id_sala).map(sala -> {
            ProveedorEntity proveedor = proveedorRepository.findById(updatedSala.getProveedor().getId_proveedor())
                    .orElseThrow(() -> new EntityNotFoundException("Proveedor not found"));

            sala.setProveedor(proveedor);
            sala.setNombre(updatedSala.getNombre());
            sala.setCapacidad(updatedSala.getCapacidad());
            sala.setPrecio(updatedSala.getPrecio());
            sala.setUbicacion(updatedSala.getUbicacion());
            sala.setEstado(updatedSala.getEstado());

            return salaRepository.save(sala);
        }).orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + id_sala));
    }

    /**
     * Delete a sala by ID.
     */
    @Transactional
    public void deleteById(Long id_sala) {
        if (!salaRepository.existsById(id_sala)) {
            throw new RuntimeException("Sala no encontrada con ID: " + id_sala);
        }
        salaRepository.deleteById(id_sala);
    }
}
