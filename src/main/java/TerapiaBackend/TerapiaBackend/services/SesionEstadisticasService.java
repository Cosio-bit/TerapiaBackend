package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.SesionEntity;
import TerapiaBackend.TerapiaBackend.repositories.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SesionEstadisticasService {

    @Autowired
    private SesionRepository sesionRepository;

    public Double getTotalSesionesFiltrado(LocalDateTime startDate, LocalDateTime endDate, String estado, Long idProfesional) {
        Double total = sesionRepository.getTotalSesionesFiltrado(startDate, endDate, estado, idProfesional);
        return total != null ? total : 0.0;
    }

    public List<SesionEntity> getSesionesFiltrado(LocalDateTime startDate, LocalDateTime endDate, String estado, Long idProfesional) {
        return sesionRepository.findSesionesFiltrado(startDate, endDate, estado, idProfesional);
    }


}
