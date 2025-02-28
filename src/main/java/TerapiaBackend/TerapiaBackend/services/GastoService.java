package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.GastoEntity;
import TerapiaBackend.TerapiaBackend.repositories.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    public List<GastoEntity> findAll() {
        return gastoRepository.findAll();
    }

    public GastoEntity findById(Long id) {
        return gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado con ID: " + id));
    }

    public GastoEntity save(GastoEntity gasto) {
        return gastoRepository.save(gasto);
    }

    public void deleteById(Long id) {
        if (!gastoRepository.existsById(id)) {
            throw new RuntimeException("Gasto no encontrado con ID: " + id);
        }
        gastoRepository.deleteById(id);
    }

    public List<GastoEntity> saveAll(List<GastoEntity> gastos) {
        return gastoRepository.saveAll(gastos);
    }

    public List<GastoEntity> findGastosByNombreAndFecha(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        return gastoRepository.findGastosByNombreAndFecha(nombre, fechaInicio, fechaFin);
    }

    public Double findAverageGastoByNombreAndFecha(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        return gastoRepository.findAverageGastoByNombreAndFecha(nombre, fechaInicio, fechaFin);
    }

    public Double findTotalGastosByFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return gastoRepository.findTotalGastosByFecha(fechaInicio, fechaFin);
    }
}
