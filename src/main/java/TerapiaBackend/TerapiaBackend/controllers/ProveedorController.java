package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProveedorEntity;
import TerapiaBackend.TerapiaBackend.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorEntity> getAllProveedores() {
        return proveedorService.findAll();
    }

    @GetMapping("/{id_proveedor}")
    public Optional<ProveedorEntity> getProveedorById(@PathVariable Long id_proveedor) {
        return proveedorService.findById(id_proveedor);
    }

    @PostMapping
    public ProveedorEntity createProveedor(@RequestBody ProveedorEntity proveedor) {
        return proveedorService.save(proveedor);
    }

    @PutMapping("/{id_proveedor}")
    public ProveedorEntity updateProveedor(@PathVariable Long id_proveedor, @RequestBody ProveedorEntity proveedor) {
        return proveedorService.update(id_proveedor, proveedor);
    }

    @DeleteMapping("/{id_proveedor}")
    public void deleteProveedor(@PathVariable Long id_proveedor) {
        proveedorService.deleteById(id_proveedor);
    }

    @PostMapping("/importar")
    public List<ProveedorEntity> crearEnLote(@RequestBody List<ProveedorEntity> proveedores) {
        return proveedorService.saveAll(proveedores);
    }
}
