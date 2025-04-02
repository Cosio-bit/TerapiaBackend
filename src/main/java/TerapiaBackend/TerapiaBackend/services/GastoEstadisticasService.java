package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.GastoEntity;
import TerapiaBackend.TerapiaBackend.repositories.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class GastoEstadisticasService {

    @Autowired
    private GastoRepository gastoRepository;


    public Double getTotalGastosFiltrado(LocalDate startDate, LocalDate endDate, String nombre, Long idProveedor) {
        Double total = gastoRepository.getTotalGastosFiltrado(startDate, endDate, nombre, idProveedor);
        return total != null ? total : 0.0;
    }

    public List<GastoEntity> getGastosFiltrado(LocalDate startDate, LocalDate endDate, String nombre, Long idProveedor) {
        return gastoRepository.findGastosFiltrado(startDate, endDate, nombre, idProveedor);
    }

}
