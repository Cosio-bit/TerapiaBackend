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

    public Optional<SesionEntity> findById(Long id) {
        return sesionRepository.findById(id);
    }

    public SesionEntity save(SesionEntity sesion) {
        return sesionRepository.save(sesion);
    }

    public void deleteById(Long id) {
        sesionRepository.deleteById(id);
    }

    public SesionEntity update(Long id, SesionEntity updatedSesion) {
        return sesionRepository.findById(id).map(sesion -> {
            sesion.setNombre(updatedSesion.getNombre());
            sesion.setPrecio(updatedSesion.getPrecio());
            sesion.setFecha_hora(updatedSesion.getFecha_hora());
            sesion.setEstado(updatedSesion.getEstado());
            sesion.setId_terapia(updatedSesion.getId_terapia());
            sesion.setId_profesional(updatedSesion.getId_profesional());
            sesion.setId_cliente(updatedSesion.getId_cliente());
            return sesionRepository.save(sesion);
        }).orElseThrow(() -> new RuntimeException("Sesión no encontrada con ID: " + id));
    }
}
