package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.SalaEntity;
import TerapiaBackend.TerapiaBackend.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public ResponseEntity<List<SalaEntity>> getAllSalas() {
        List<SalaEntity> salas = salaService.findAll();
        return salas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(salas);
    }

    @GetMapping("/{id_sala}")
    public ResponseEntity<SalaEntity> getSalaById(@PathVariable Long id_sala) {
        return salaService.findById(id_sala)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SalaEntity> createSala(@RequestBody SalaEntity sala) {
        return ResponseEntity.ok(salaService.save(sala));
    }

    @PutMapping("/{id_sala}")
    public ResponseEntity<SalaEntity> updateSala(@PathVariable Long id_sala, @RequestBody SalaEntity sala) {
        return ResponseEntity.ok(salaService.update(id_sala, sala));
    }

    @DeleteMapping("/{id_sala}")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id_sala) {
        salaService.deleteById(id_sala);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<SalaEntity>> crearEnLote(@RequestBody List<SalaEntity> salas) {
        return ResponseEntity.ok(salaService.saveAll(salas));
    }
}
