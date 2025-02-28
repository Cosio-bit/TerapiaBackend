package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.TerapiaEntity;
import TerapiaBackend.TerapiaBackend.services.TerapiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terapias")
public class TerapiaController {

    @Autowired
    private TerapiaService terapiaService;

    @GetMapping
    public ResponseEntity<List<TerapiaEntity>> getAllTerapias() {
        List<TerapiaEntity> terapias = terapiaService.findAll();
        return terapias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(terapias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerapiaEntity> getTerapiaById(@PathVariable Long id) {
        return terapiaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TerapiaEntity> createTerapia(@RequestBody TerapiaEntity terapia) {
        return ResponseEntity.ok(terapiaService.createOrUpdateTerapia(terapia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerapiaEntity> updateTerapia(@PathVariable Long id, @RequestBody TerapiaEntity terapia) {
        return ResponseEntity.ok(terapiaService.createOrUpdateTerapia(terapia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerapia(@PathVariable Long id) {
        terapiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<TerapiaEntity>> crearEnLote(@RequestBody List<TerapiaEntity> terapias) {
        return ResponseEntity.ok(terapiaService.saveAll(terapias));
    }
}
