package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ClienteEntity;
import TerapiaBackend.TerapiaBackend.entities.FichaSaludEntity;
import TerapiaBackend.TerapiaBackend.repositories.ClienteRepository;
import TerapiaBackend.TerapiaBackend.repositories.FichaSaludRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FichaSaludRepository fichaSaludRepository;

    // ✅ Fetch all clients
    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    // ✅ Find client by ID
    public Optional<ClienteEntity> findById(Long id) {
        return clienteRepository.findById(id);
    }
    @Transactional
    public ClienteEntity save(ClienteEntity cliente) {
        // Ensure the list is initialized
        if (cliente.getFichasSalud() == null) {
            cliente.setFichasSalud(new ArrayList<>());
        }

        List<FichaSaludEntity> savedFichas = new ArrayList<>();
        for (FichaSaludEntity ficha : cliente.getFichasSalud()) {
            // Save each FichaSaludEntity explicitly
            savedFichas.add(fichaSaludRepository.save(ficha));
        }

        cliente.setFichasSalud(savedFichas);
        return clienteRepository.save(cliente);
    }
    @Transactional
    public Optional<ClienteEntity> update(Long id, ClienteEntity updatedCliente) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setUsuario(updatedCliente.getUsuario());
            cliente.setFecha_registro(updatedCliente.getFecha_registro());
            cliente.setSaldo(updatedCliente.getSaldo());

            List<FichaSaludEntity> updatedFichas = new ArrayList<>();
            if (updatedCliente.getFichasSalud() != null) {
                for (FichaSaludEntity ficha : updatedCliente.getFichasSalud()) {
                    if (ficha.getId_fichasalud() != null) {
                        // Update existing ficha
                        FichaSaludEntity finalFicha = ficha;
                        FichaSaludEntity finalFicha1 = ficha;
                        ficha = fichaSaludRepository.findById(ficha.getId_fichasalud())
                                .map(existingFicha -> {
                                    existingFicha.setFecha(finalFicha.getFecha());
                                    existingFicha.setDescripcion(finalFicha.getDescripcion());
                                    return fichaSaludRepository.save(existingFicha);
                                }).orElseGet(() -> fichaSaludRepository.save(finalFicha1)); // Save new if not found
                    } else {
                        // Save new ficha
                        ficha = fichaSaludRepository.save(ficha);
                    }
                    updatedFichas.add(ficha);
                }
            }

            cliente.setFichasSalud(updatedFichas);
            return clienteRepository.save(cliente);
        });
    }


    // ✅ Delete client
    @Transactional
    public boolean deleteById(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Save multiple clients
    @Transactional
    public List<ClienteEntity> saveAll(List<ClienteEntity> clientes) {
        return clienteRepository.saveAll(clientes);
    }

    // ✅ Fetch fichas associated with a client
    public List<FichaSaludEntity> findFichasSaludByCliente(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteEntity::getFichasSalud)
                .orElse(new ArrayList<>());
    }
}
