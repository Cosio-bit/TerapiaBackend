package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ArriendoEntity;
import TerapiaBackend.TerapiaBackend.services.ArriendoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/arriendos")
public class ArriendoController {

    @Autowired
    private ArriendoService arriendoService;

    /**
     * Fetch all arriendos.
     */
    @GetMapping
    public ResponseEntity<List<ArriendoEntity>> obtenerTodosLosArriendos() {
        List<ArriendoEntity> arriendos = arriendoService.findAll();
        return arriendos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(arriendos);
    }

    /**
     * Fetch a single arriendo by ID.
     */
    @GetMapping("/{id_arriendo}")
    public ResponseEntity<ArriendoEntity> obtenerArriendoPorId(@PathVariable Long id_arriendo) {
        Optional<ArriendoEntity> arriendo = arriendoService.findById(id_arriendo);
        return arriendo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new arriendo.
     */
    @Transactional
    @PostMapping
    public ResponseEntity<?> crearArriendo(@RequestBody ArriendoEntity arriendo) {
        if (arriendo.getSala() == null || arriendo.getCliente() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: sala o cliente.");
        }
        return ResponseEntity.ok(arriendoService.save(arriendo));
    }

    /**
     * Update an existing arriendo.
     */
    @Transactional
    @PutMapping("/{id_arriendo}")
    public ResponseEntity<?> actualizarArriendo(@PathVariable Long id_arriendo, @RequestBody ArriendoEntity arriendo) {
        if (arriendo.getSala() == null || arriendo.getCliente() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: sala o cliente.");
        }
        return ResponseEntity.ok(arriendoService.update(id_arriendo, arriendo));
    }

    /**
     * Delete an arriendo by ID.
     */
    @DeleteMapping("/{id_arriendo}")
    public ResponseEntity<?> eliminarArriendo(@PathVariable Long id_arriendo) {
        try {
            arriendoService.deleteById(id_arriendo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{id_cliente}")
    public ResponseEntity<List<ArriendoEntity>> obtenerArriendosPorCliente(@PathVariable Long id_cliente) {
        List<ArriendoEntity> arriendos = arriendoService.findByClienteId(id_cliente);
        return arriendos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(arriendos);
    }

}
