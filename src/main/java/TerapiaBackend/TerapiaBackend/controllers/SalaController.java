package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.SalaEntity;
import TerapiaBackend.TerapiaBackend.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    /**
     * Fetch all salas.
     */
    @GetMapping
    public ResponseEntity<List<SalaEntity>> obtenerTodasLasSalas() {
        List<SalaEntity> salas = salaService.findAll();
        return salas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(salas);
    }

    /**
     * Fetch a single sala by ID.
     */
    @GetMapping("/{id_sala}")
    public ResponseEntity<SalaEntity> obtenerSalaPorId(@PathVariable Long id_sala) {
        Optional<SalaEntity> sala = salaService.findById(id_sala);
        return sala.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new sala.
     */
    @Transactional
    @PostMapping
    public ResponseEntity<?> crearSala(@RequestBody SalaEntity sala) {
        if (sala.getProveedor() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: proveedor.");
        }
        return ResponseEntity.ok(salaService.save(sala));
    }

    /**
     * Update an existing sala.
     */
    @Transactional
    @PutMapping("/{id_sala}")
    public ResponseEntity<?> actualizarSala(@PathVariable Long id_sala, @RequestBody SalaEntity sala) {
        if (sala.getProveedor() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: proveedor.");
        }
        return ResponseEntity.ok(salaService.update(id_sala, sala));
    }

    /**
     * Delete a sala by ID.
     */
    @DeleteMapping("/{id_sala}")
    public ResponseEntity<?> eliminarSala(@PathVariable Long id_sala) {
        try {
            salaService.deleteById(id_sala);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
