package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.SesionEntity;
import TerapiaBackend.TerapiaBackend.repositories.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    public List<SesionEntity> findAll() {
        return sesionRepository.findAll();
    }

    public Optional<SesionEntity> findById(Long id_sesion) {
        return sesionRepository.findById(id_sesion);
    }

    public SesionEntity save(SesionEntity sesion) {
        return sesionRepository.save(sesion);
    }

    public SesionEntity update(Long id_sesion, SesionEntity updatedSesion) {
        return sesionRepository.findById(id_sesion).map(sesion -> {
            sesion.setFecha_hora(updatedSesion.getFecha_hora());
            sesion.setEstado(updatedSesion.getEstado());
            sesion.setPrecio(updatedSesion.getPrecio());
            sesion.setProfesional(updatedSesion.getProfesional());
            return sesionRepository.save(sesion);
        }).orElseThrow(() -> new RuntimeException("Sesión no encontrada con ID: " + id_sesion));
    }

    public void deleteById(Long id_sesion) {
        if (!sesionRepository.existsById(id_sesion)) {
            throw new RuntimeException("Sesión no encontrada con ID: " + id_sesion);
        }
        sesionRepository.deleteById(id_sesion);
    }

    public List<SesionEntity> importarSesiones(List<SesionEntity> sesiones) {
        return sesionRepository.saveAll(sesiones);
    }
}
