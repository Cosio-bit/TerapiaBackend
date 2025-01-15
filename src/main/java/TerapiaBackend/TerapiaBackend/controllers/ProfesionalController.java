package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProfesionalEntity;
import TerapiaBackend.TerapiaBackend.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @GetMapping
    public List<ProfesionalEntity> getAllProfesionales() {
        return profesionalService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProfesionalEntity> getProfesionalById(@PathVariable Long id) {
        return profesionalService.findById(id);
    }

    @PostMapping
    public ProfesionalEntity createProfesional(@RequestBody ProfesionalEntity profesional) {
        return profesionalService.save(profesional);
    }

    @PutMapping("/{id}")
    public ProfesionalEntity updateProfesional(@PathVariable Long id, @RequestBody ProfesionalEntity profesional) {
        return profesionalService.update(id, profesional);
    }

    @DeleteMapping("/{id}")
    public void deleteProfesional(@PathVariable Long id) {
        profesionalService.deleteById(id);
    }
}
