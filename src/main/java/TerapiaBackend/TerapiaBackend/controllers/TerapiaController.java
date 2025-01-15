package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.TerapiaEntity;
import TerapiaBackend.TerapiaBackend.services.TerapiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/terapias")
public class TerapiaController {

    @Autowired
    private TerapiaService terapiaService;

    @GetMapping
    public List<TerapiaEntity> getAllTerapias() {
        return terapiaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TerapiaEntity> getTerapiaById(@PathVariable Long id) {
        return terapiaService.findById(id);
    }

    @PostMapping
    public TerapiaEntity createTerapia(@RequestBody TerapiaEntity terapia) {
        return terapiaService.save(terapia);
    }

    @PutMapping("/{id}")
    public TerapiaEntity updateTerapia(@PathVariable Long id, @RequestBody TerapiaEntity terapia) {
        return terapiaService.update(id, terapia);
    }

    @DeleteMapping("/{id}")
    public void deleteTerapia(@PathVariable Long id) {
        terapiaService.deleteById(id);
    }
}
