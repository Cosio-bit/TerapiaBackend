package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.FichaSaludEntity;
import TerapiaBackend.TerapiaBackend.services.FichaSaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fichasalud")
public class FichaSaludController {

    @Autowired
    private FichaSaludService fichaSaludService;

    @GetMapping
    public List<FichaSaludEntity> getAllFichasSalud() {
        return fichaSaludService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<FichaSaludEntity> getFichaSaludById(@PathVariable Long id) {
        return fichaSaludService.findById(id);
    }

    @PostMapping
    public FichaSaludEntity createFichaSalud(@RequestBody FichaSaludEntity fichaSalud) {
        return fichaSaludService.save(fichaSalud);
    }

    @PutMapping("/{id}")
    public FichaSaludEntity updateFichaSalud(@PathVariable Long id, @RequestBody FichaSaludEntity fichaSalud) {
        return fichaSaludService.update(id, fichaSalud);
    }

    @DeleteMapping("/{id}")
    public void deleteFichaSalud(@PathVariable Long id) {
        fichaSaludService.deleteById(id);
    }
}
