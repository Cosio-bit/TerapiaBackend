package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ClienteEntity;
import TerapiaBackend.TerapiaBackend.entities.FichaSaludEntity;
import TerapiaBackend.TerapiaBackend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteEntity> obtenerTodos() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> obtenerPorId(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> crear(@RequestBody ClienteEntity cliente) {
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> actualizar(@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        Optional<ClienteEntity> updatedCliente = clienteService.update(id, cliente);
        return updatedCliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (clienteService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/importar")
    public List<ClienteEntity> crearEnLote(@RequestBody List<ClienteEntity> clientes) {
        return clienteService.saveAll(clientes);
    }

    @GetMapping("/{id}/fichas-salud")
    public ResponseEntity<List<FichaSaludEntity>> obtenerFichasSaludPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findFichasSaludByCliente(id));
    }
}
