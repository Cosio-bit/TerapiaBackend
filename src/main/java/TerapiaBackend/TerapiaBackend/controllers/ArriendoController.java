package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ArriendoEntity;
import TerapiaBackend.TerapiaBackend.services.ArriendoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arriendos")
public class ArriendoController {

    @Autowired
    private ArriendoService arriendoService;

    @GetMapping
    public List<ArriendoEntity> getAll() {
        return arriendoService.findAll();
    }

    @PostMapping
    public ArriendoEntity create(@RequestBody ArriendoEntity arriendo) {
        return arriendoService.save(arriendo);
    }

    @GetMapping("/estado/{estado}")
    public List<ArriendoEntity> getByEstado(@PathVariable String estado) {
        return arriendoService.findByEstado(estado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        arriendoService.deleteById(id);
    }
}
