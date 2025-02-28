package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.FichaSaludEntity;
import TerapiaBackend.TerapiaBackend.repositories.FichaSaludRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FichaSaludService {

    @Autowired
    private FichaSaludRepository fichaSaludRepository;

    public List<FichaSaludEntity> findAll() {
        return fichaSaludRepository.findAll();
    }

    public Optional<FichaSaludEntity> findById(Long id_fichasalud) {
        return fichaSaludRepository.findById(id_fichasalud);
    }

    public FichaSaludEntity save(FichaSaludEntity fichaSalud) {
        return fichaSaludRepository.save(fichaSalud);
    }

    public FichaSaludEntity update(Long id_fichasalud, FichaSaludEntity updatedFichaSalud) {
        return fichaSaludRepository.findById(id_fichasalud).map(fichaSalud -> {
            fichaSalud.setFecha(updatedFichaSalud.getFecha());
            fichaSalud.setDescripcion(updatedFichaSalud.getDescripcion());
            return fichaSaludRepository.save(fichaSalud);
        }).orElseThrow(() -> new RuntimeException("Ficha de salud no encontrada con ID: " + id_fichasalud));
    }

    public void deleteById(Long id_fichasalud) {
        fichaSaludRepository.deleteById(id_fichasalud);
    }

    public List<FichaSaludEntity> saveAll(List<FichaSaludEntity> fichasSalud) {
        return fichaSaludRepository.saveAll(fichasSalud);
    }
}
