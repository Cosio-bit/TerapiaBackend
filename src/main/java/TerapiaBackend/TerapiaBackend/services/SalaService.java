package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.SalaEntity;
import TerapiaBackend.TerapiaBackend.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<SalaEntity> findAll() {
        return salaRepository.findAll();
    }

    public Optional<SalaEntity> findById(Long id) {
        return salaRepository.findById(id);
    }

    public SalaEntity save(SalaEntity sala) {
        return salaRepository.save(sala);
    }

    public void deleteById(Long id) {
        salaRepository.deleteById(id);
    }

    public SalaEntity update(Long id, SalaEntity updatedSala) {
        return salaRepository.findById(id).map(sala -> {
            sala.setNombre(updatedSala.getNombre());
            sala.setCapacidad(updatedSala.getCapacidad());
            sala.setPrecio(updatedSala.getPrecio());
            sala.setUbicacion(updatedSala.getUbicacion());
            sala.setEstado(updatedSala.getEstado());
            sala.setId_proveedor(updatedSala.getId_proveedor());
            return salaRepository.save(sala);
        }).orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + id));
    }
}
