package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProveedorEntity;
import TerapiaBackend.TerapiaBackend.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorEntity> getAllProveedores() {
        return proveedorService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProveedorEntity> getProveedorById(@PathVariable Long id) {
        return proveedorService.findById(id);
    }

    @PostMapping
    public ProveedorEntity createProveedor(@RequestBody ProveedorEntity proveedor) {
        return proveedorService.save(proveedor);
    }

    @PutMapping("/{id}")
    public ProveedorEntity updateProveedor(@PathVariable Long id, @RequestBody ProveedorEntity proveedor) {
        return proveedorService.update(id, proveedor);
    }

    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteById(id);
    }
}
