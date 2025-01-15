package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.SalaEntity;
import TerapiaBackend.TerapiaBackend.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public List<SalaEntity> getAllSalas() {
        return salaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<SalaEntity> getSalaById(@PathVariable Long id) {
        return salaService.findById(id);
    }

    @PostMapping
    public SalaEntity createSala(@RequestBody SalaEntity sala) {
        return salaService.save(sala);
    }

    @PutMapping("/{id}")
    public SalaEntity updateSala(@PathVariable Long id, @RequestBody SalaEntity sala) {
        return salaService.update(id, sala);
    }

    @DeleteMapping("/{id}")
    public void deleteSala(@PathVariable Long id) {
        salaService.deleteById(id);
    }
}
