package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.SesionEntity;
import TerapiaBackend.TerapiaBackend.services.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sesiones")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping
    public List<SesionEntity> getAllSesiones() {
        return sesionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<SesionEntity> getSesionById(@PathVariable Long id) {
        return sesionService.findById(id);
    }

    @PostMapping
    public SesionEntity createSesion(@RequestBody SesionEntity sesion) {
        return sesionService.save(sesion);
    }

    @PutMapping("/{id}")
    public SesionEntity updateSesion(@PathVariable Long id, @RequestBody SesionEntity sesion) {
        return sesionService.update(id, sesion);
    }

    @DeleteMapping("/{id}")
    public void deleteSesion(@PathVariable Long id) {
        sesionService.deleteById(id);
    }
}
