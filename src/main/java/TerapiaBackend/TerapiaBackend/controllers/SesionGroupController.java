package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.SesionGroupEntity;
import TerapiaBackend.TerapiaBackend.services.SesionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/sesion-groups")
public class SesionGroupController {

    @Autowired
    private SesionGroupService sesionGroupService;

    @GetMapping
    public ResponseEntity<List<SesionGroupEntity>> obtenerTodosLosSesionGroups() {
        List<SesionGroupEntity> sesionGroups = sesionGroupService.findAll();
        return sesionGroups.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sesionGroups);
    }

    @GetMapping("/{id_sesion_group}")
    public ResponseEntity<SesionGroupEntity> obtenerSesionGroupPorId(@PathVariable Long id_sesion_group) {
        return sesionGroupService.findById(id_sesion_group)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> crearSesionGroup(@RequestBody SesionGroupEntity sesionGroup) {
        if (sesionGroup.getTerapia() == null || sesionGroup.getCliente() == null || sesionGroup.getVariante() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: terapia, cliente o variante.");
        }
        return ResponseEntity.ok(sesionGroupService.save(sesionGroup));
    }

    @Transactional
    @PutMapping("/{id_sesion_group}")
    public ResponseEntity<?> actualizarSesionGroup(@PathVariable Long id_sesion_group, @RequestBody SesionGroupEntity sesionGroup) {
        if (sesionGroup.getTerapia() == null || sesionGroup.getCliente() == null || sesionGroup.getVariante() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios: terapia, cliente o variante.");
        }
        return ResponseEntity.ok(sesionGroupService.update(id_sesion_group, sesionGroup));
    }

    @DeleteMapping("/{id_sesion_group}")
    public ResponseEntity<?> eliminarSesionGroup(@PathVariable Long id_sesion_group) {
        try {
            sesionGroupService.deleteById(id_sesion_group);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/importar")
    public ResponseEntity<List<SesionGroupEntity>> crearEnLote(@RequestBody List<SesionGroupEntity> sesionGroups) {
        return ResponseEntity.ok(sesionGroupService.importarSesionGroups(sesionGroups));
    }
}
