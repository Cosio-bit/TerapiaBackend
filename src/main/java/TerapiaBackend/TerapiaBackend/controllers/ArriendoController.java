
package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ArriendoEntity;
import TerapiaBackend.TerapiaBackend.services.ArriendoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/arriendos")
public class ArriendoController {

    @Autowired
    private ArriendoService arriendoService;

    // Obtener todos los arriendos
    @GetMapping
    public List<ArriendoEntity> obtenerTodos() {
        return arriendoService.findAll();
    }

    // Obtener arriendo por ID
    @GetMapping("/{id}")
    public ResponseEntity<ArriendoEntity> obtenerPorId(@PathVariable Long id) {
        return arriendoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo arriendo
    @PostMapping
    public ResponseEntity<ArriendoEntity> crear(@RequestBody ArriendoEntity arriendo) {
        return ResponseEntity.ok(arriendoService.save(arriendo));
    }

    // Actualizar un arriendo existente
    @PutMapping("/{id}")
    public ResponseEntity<ArriendoEntity> actualizar(@PathVariable Long id, @RequestBody ArriendoEntity arriendo) {
        return arriendoService.update(id, arriendo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un arriendo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (arriendoService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Obtener horarios disponibles para una sala en una fecha
    @GetMapping("/disponibles/{idSala}/{fecha}")
    public ResponseEntity<List<Map<String, String>>> obtenerHorariosDisponibles(
            @PathVariable Long idSala,
            @PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        return ResponseEntity.ok(arriendoService.getAvailableHours(idSala, localDate));
    }

    // Crear múltiples arriendos
    @PostMapping("/importar")
    public ResponseEntity<List<ArriendoEntity>> crearEnLote(@RequestBody List<ArriendoEntity> arriendos) {
        return ResponseEntity.ok(arriendoService.saveAll(arriendos));
    }

}
