package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.FichaSaludEntity;
import TerapiaBackend.TerapiaBackend.services.FichaSaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fichas-salud")
public class FichaSaludController {

    @Autowired
    private FichaSaludService fichaSaludService;

    @GetMapping
    public List<FichaSaludEntity> obtenerTodas() {
        return fichaSaludService.findAll();
    }

    @GetMapping("/{id_fichasalud}")
    public Optional<FichaSaludEntity> obtenerPorId(@PathVariable Long id_fichasalud) {
        return fichaSaludService.findById(id_fichasalud);
    }

    @PostMapping
    public FichaSaludEntity crear(@RequestBody FichaSaludEntity fichaSalud) {
        return fichaSaludService.save(fichaSalud);
    }

    @PutMapping("/{id_fichasalud}")
    public FichaSaludEntity actualizar(@PathVariable Long id_fichasalud, @RequestBody FichaSaludEntity fichaSalud) {
        return fichaSaludService.update(id_fichasalud, fichaSalud);
    }

    @DeleteMapping("/{id_fichasalud}")
    public void eliminar(@PathVariable Long id_fichasalud) {
        fichaSaludService.deleteById(id_fichasalud);
    }

    @PostMapping("/importar")
    public List<FichaSaludEntity> crearEnLote(@RequestBody List<FichaSaludEntity> fichasSalud) {
        return fichaSaludService.saveAll(fichasSalud);
    }
}
