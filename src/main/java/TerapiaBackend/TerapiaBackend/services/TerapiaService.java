package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.TerapiaEntity;
import TerapiaBackend.TerapiaBackend.repositories.TerapiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerapiaService {

    @Autowired
    private TerapiaRepository terapiaRepository;

    public List<TerapiaEntity> findAll() {
        return terapiaRepository.findAll();
    }

    public Optional<TerapiaEntity> findById(Long id) {
        return terapiaRepository.findById(id);
    }

    public TerapiaEntity save(TerapiaEntity terapia) {
        return terapiaRepository.save(terapia);
    }

    public void deleteById(Long id) {
        terapiaRepository.deleteById(id);
    }

    public TerapiaEntity update(Long id, TerapiaEntity updatedTerapia) {
        return terapiaRepository.findById(id).map(terapia -> {
            terapia.setNombre(updatedTerapia.getNombre());
            terapia.setDescripcion(updatedTerapia.getDescripcion());
            terapia.setFecha_inicio(updatedTerapia.getFecha_inicio());
            terapia.setFecha_fin(updatedTerapia.getFecha_fin());
            terapia.setPrecio(updatedTerapia.getPrecio());
            terapia.setCantidad_sesiones(updatedTerapia.getCantidad_sesiones());
            terapia.setEstado(updatedTerapia.getEstado());
            return terapiaRepository.save(terapia);
        }).orElseThrow(() -> new RuntimeException("Terapia no encontrada con ID: " + id));
    }
}
