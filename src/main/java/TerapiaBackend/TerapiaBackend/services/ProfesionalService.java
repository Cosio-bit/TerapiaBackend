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

    public Optional<ProfesionalEntity> findById(Long id_profesional) {
        return profesionalRepository.findById(id_profesional);
    }

    public ProfesionalEntity save(ProfesionalEntity profesional) {
        return profesionalRepository.save(profesional);
    }

    public List<ProfesionalEntity> saveAll(List<ProfesionalEntity> profesionales) {
        return profesionalRepository.saveAll(profesionales);
    }

    public ProfesionalEntity update(Long id_profesional, ProfesionalEntity updatedProfesional) {
        return profesionalRepository.findById(id_profesional).map(profesional -> {
            profesional.setUsuario(updatedProfesional.getUsuario());
            profesional.setEspecialidad(updatedProfesional.getEspecialidad());
            profesional.setCertificaciones(updatedProfesional.getCertificaciones());
            profesional.setDisponibilidad(updatedProfesional.getDisponibilidad());
            profesional.setBanco(updatedProfesional.getBanco());
            profesional.setNro_cuenta_bancaria(updatedProfesional.getNro_cuenta_bancaria());
            return profesionalRepository.save(profesional);
        }).orElseThrow(() -> new RuntimeException("Profesional no encontrado con ID: " + id_profesional));
    }

    public void deleteById(Long id_profesional) {
        if (!profesionalRepository.existsById(id_profesional)) {
            throw new RuntimeException("Profesional no encontrado con ID: " + id_profesional);
        }
        profesionalRepository.deleteById(id_profesional);
    }
}
