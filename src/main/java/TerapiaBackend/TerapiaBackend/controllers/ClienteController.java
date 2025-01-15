package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ClienteEntity;
import TerapiaBackend.TerapiaBackend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteEntity> getAllClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ClienteEntity> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public ClienteEntity createCliente(@RequestBody ClienteEntity cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public ClienteEntity updateCliente(@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
    }
}
