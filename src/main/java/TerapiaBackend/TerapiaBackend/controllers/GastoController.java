package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.GastoEntity;
import TerapiaBackend.TerapiaBackend.services.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @GetMapping
    public ResponseEntity<List<GastoEntity>> getAll() {
        List<GastoEntity> gastos = gastoService.findAll();
        return gastos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(gastos);
    }

    @GetMapping("/{id_gasto}")
    public ResponseEntity<GastoEntity> getById(@PathVariable Long id_gasto) {
        return ResponseEntity.ok(gastoService.findById(id_gasto));
    }

    @PostMapping
    public ResponseEntity<GastoEntity> createOrUpdate(@RequestBody GastoEntity gasto) {
        return ResponseEntity.ok(gastoService.save(gasto));
    }

    @DeleteMapping("/{id_gasto}")
    public ResponseEntity<Void> delete(@PathVariable Long id_gasto) {
        gastoService.deleteById(id_gasto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<GastoEntity>> crearEnLote(@RequestBody List<GastoEntity> gastos) {
        return ResponseEntity.ok(gastoService.saveAll(gastos));
    }

    // Buscar gastos por nombre y rango de fechas
    @GetMapping("/buscar")
    public ResponseEntity<List<GastoEntity>> findGastosByNombreAndFecha(@RequestParam String nombre,
                                                                        @RequestParam LocalDate fechaInicio,
                                                                        @RequestParam LocalDate fechaFin) {
        List<GastoEntity> gastos = gastoService.findGastosByNombreAndFecha(nombre, fechaInicio, fechaFin);
        return gastos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(gastos);
    }

    // Calcular promedio de gastos por nombre y fechas
    @GetMapping("/promedio")
    public ResponseEntity<Double> findAverageGastoByNombreAndFecha(@RequestParam String nombre,
                                                                   @RequestParam LocalDate fechaInicio,
                                                                   @RequestParam LocalDate fechaFin) {
        Double promedio = gastoService.findAverageGastoByNombreAndFecha(nombre, fechaInicio, fechaFin);
        return ResponseEntity.ok(promedio != null ? promedio : 0.0);
    }

    // Total de gastos en un rango de fechas
    @GetMapping("/total")
    public ResponseEntity<Double> findTotalGastosByFecha(@RequestParam LocalDate fechaInicio,
                                                         @RequestParam LocalDate fechaFin) {
        Double total = gastoService.findTotalGastosByFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(total != null ? total : 0.0);
    }
}
