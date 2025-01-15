package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ClienteEntity;
import TerapiaBackend.TerapiaBackend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteEntity save(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public ClienteEntity update(Long id, ClienteEntity updatedCliente) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setId_usuario(updatedCliente.getId_usuario());
            cliente.setFecha_registro(updatedCliente.getFecha_registro());
            cliente.setSaldo(updatedCliente.getSaldo());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }
}
