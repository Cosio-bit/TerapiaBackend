package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.FichaSaludEntity;
import TerapiaBackend.TerapiaBackend.repositories.FichaSaludRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaSaludService {

    @Autowired
    private FichaSaludRepository fichaSaludRepository;

    public List<FichaSaludEntity> findAll() {
        return fichaSaludRepository.findAll();
    }

    public Optional<FichaSaludEntity> findById(Long id) {
        return fichaSaludRepository.findById(id);
    }

    public FichaSaludEntity save(FichaSaludEntity fichaSalud) {
        return fichaSaludRepository.save(fichaSalud);
    }

    public void deleteById(Long id) {
        fichaSaludRepository.deleteById(id);
    }

    public FichaSaludEntity update(Long id, FichaSaludEntity updatedFichaSalud) {
        return fichaSaludRepository.findById(id).map(fichaSalud -> {
            fichaSalud.setFecha(updatedFichaSalud.getFecha());
            fichaSalud.setDescripcion(updatedFichaSalud.getDescripcion());
            fichaSalud.setId_cliente(updatedFichaSalud.getId_cliente());
            return fichaSaludRepository.save(fichaSalud);
        }).orElseThrow(() -> new RuntimeException("Ficha de salud no encontrada con ID: " + id));
    }
}
