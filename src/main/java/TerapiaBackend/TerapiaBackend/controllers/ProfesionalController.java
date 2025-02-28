package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProfesionalEntity;
import TerapiaBackend.TerapiaBackend.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @GetMapping
    public ResponseEntity<List<ProfesionalEntity>> getAllProfesionales() {
        List<ProfesionalEntity> profesionales = profesionalService.findAll();
        return profesionales.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(profesionales);
    }

    @GetMapping("/{id_profesional}")
    public ResponseEntity<ProfesionalEntity> getProfesionalById(@PathVariable Long id_profesional) {
        return profesionalService.findById(id_profesional)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProfesionalEntity> createProfesional(@RequestBody ProfesionalEntity profesional) {
        return ResponseEntity.ok(profesionalService.save(profesional));
    }

    @PutMapping("/{id_profesional}")
    public ResponseEntity<ProfesionalEntity> updateProfesional(@PathVariable Long id_profesional, @RequestBody ProfesionalEntity profesional) {
        return ResponseEntity.ok(profesionalService.update(id_profesional, profesional));
    }

    @DeleteMapping("/{id_profesional}")
    public ResponseEntity<Void> deleteProfesional(@PathVariable Long id_profesional) {
        profesionalService.deleteById(id_profesional);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/importar")
    public ResponseEntity<List<ProfesionalEntity>> crearEnLote(@RequestBody List<ProfesionalEntity> profesionales) {
        return ResponseEntity.ok(profesionalService.saveAll(profesionales));
    }

}
