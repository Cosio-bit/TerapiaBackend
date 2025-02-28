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

    public Optional<SalaEntity> findById(Long id_sala) {
        return salaRepository.findById(id_sala);
    }

    public SalaEntity save(SalaEntity sala) {
        return salaRepository.save(sala);
    }

    public SalaEntity update(Long id_sala, SalaEntity updatedSala) {
        return salaRepository.findById(id_sala).map(sala -> {
            sala.setNombre(updatedSala.getNombre());
            sala.setCapacidad(updatedSala.getCapacidad());
            sala.setUbicacion(updatedSala.getUbicacion());
            sala.setEstado(updatedSala.getEstado());
            return salaRepository.save(sala);
        }).orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + id_sala));
    }

    public void deleteById(Long id_sala) {
        if (!salaRepository.existsById(id_sala)) {
            throw new RuntimeException("Sala no encontrada con ID: " + id_sala);
        }
        salaRepository.deleteById(id_sala);
    }

    public List<SalaEntity> saveAll(List<SalaEntity> salas) {
        return salaRepository.saveAll(salas);
    }
}
