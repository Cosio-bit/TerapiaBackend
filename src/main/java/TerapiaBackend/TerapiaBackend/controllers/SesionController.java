package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.SesionEntity;
import TerapiaBackend.TerapiaBackend.services.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping
    public ResponseEntity<List<SesionEntity>> obtenerTodasLasSesiones() {
        List<SesionEntity> sesiones = sesionService.findAll();
        return sesiones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sesiones);
    }

    @GetMapping("/{id_sesion}")
    public ResponseEntity<SesionEntity> obtenerSesionPorId(@PathVariable Long id_sesion) {
        return sesionService.findById(id_sesion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SesionEntity> crearSesion(@RequestBody SesionEntity sesion) {
        return ResponseEntity.ok(sesionService.save(sesion));
    }

    @PutMapping("/{id_sesion}")
    public ResponseEntity<SesionEntity> actualizarSesion(@PathVariable Long id_sesion, @RequestBody SesionEntity sesion) {
        return ResponseEntity.ok(sesionService.update(id_sesion, sesion));
    }

    @DeleteMapping("/{id_sesion}")
    public ResponseEntity<Void> eliminarSesion(@PathVariable Long id_sesion) {
        sesionService.deleteById(id_sesion);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<List<SesionEntity>> crearEnLote(@RequestBody List<SesionEntity> sesiones) {
        return ResponseEntity.ok(sesionService.importarSesiones(sesiones));
    }
}
