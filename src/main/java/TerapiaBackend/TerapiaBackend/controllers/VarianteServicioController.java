package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.VarianteServicioEntity;
import TerapiaBackend.TerapiaBackend.services.VarianteServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VarianteServicioController {

    private final VarianteServicioService varianteServicioService;

    public VarianteServicioController(VarianteServicioService varianteServicioService) {
        this.varianteServicioService = varianteServicioService;
    }

    @GetMapping
    public ResponseEntity<List<VarianteServicioEntity>> getAll() {
        List<VarianteServicioEntity> variantes = varianteServicioService.findAll();
        return variantes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(variantes);
    }

    @GetMapping("/{id_variante}")
    public ResponseEntity<VarianteServicioEntity> getById(@PathVariable Long id_variante) {
        return ResponseEntity.ok(varianteServicioService.findById(id_variante));
    }

    @PostMapping
    public ResponseEntity<VarianteServicioEntity> create(@RequestBody VarianteServicioEntity varianteServicio) {
        return ResponseEntity.status(201).body(varianteServicioService.save(varianteServicio));
    }

    @PutMapping("/{id_variante}")
    public ResponseEntity<VarianteServicioEntity> update(@PathVariable Long id_variante, @RequestBody VarianteServicioEntity varianteServicio) {
        return ResponseEntity.ok(varianteServicioService.update(id_variante, varianteServicio));
    }

    @DeleteMapping("/{id_variante}")
    public ResponseEntity<Void> delete(@PathVariable Long id_variante) {
        varianteServicioService.deleteById(id_variante);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<VarianteServicioEntity>> crearEnLote(@RequestBody List<VarianteServicioEntity> variantes) {
        return ResponseEntity.ok(varianteServicioService.saveAll(variantes));
    }

}
