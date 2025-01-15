package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProfesionalEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    public List<ProfesionalEntity> findAll() {
        return profesionalRepository.findAll();
    }

    public Optional<ProfesionalEntity> findById(Long id) {
        return profesionalRepository.findById(id);
    }

    public ProfesionalEntity save(ProfesionalEntity profesional) {
        return profesionalRepository.save(profesional);
    }

    public void deleteById(Long id) {
        profesionalRepository.deleteById(id);
    }

    public ProfesionalEntity update(Long id, ProfesionalEntity updatedProfesional) {
        return profesionalRepository.findById(id).map(profesional -> {
            profesional.setId_usuario(updatedProfesional.getId_usuario());
            profesional.setEspecialidad(updatedProfesional.getEspecialidad());
            profesional.setCertificaciones(updatedProfesional.getCertificaciones());
            profesional.setDisponibilidad(updatedProfesional.getDisponibilidad());
            profesional.setBanco(updatedProfesional.getBanco());
            profesional.setNro_cuenta_bancaria(updatedProfesional.getNro_cuenta_bancaria());
            return profesionalRepository.save(profesional);
        }).orElseThrow(() -> new RuntimeException("Profesional no encontrado con ID: " + id));
    }
}
