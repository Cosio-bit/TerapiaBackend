package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.*;
import TerapiaBackend.TerapiaBackend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArriendoService {

    @Autowired
    private ArriendoRepository arriendoRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Fetch all arriendos.
     */
    public List<ArriendoEntity> findAll() {
        return arriendoRepository.findAll();
    }

    /**
     * Fetch a single arriendo by ID.
     */
    public Optional<ArriendoEntity> findById(Long id_arriendo) {
        return arriendoRepository.findById(id_arriendo);
    }

    /**
     * Save a new arriendo, ensuring sala and cliente exist.
     */
    @Transactional
    public ArriendoEntity save(ArriendoEntity arriendo) {
        // ✅ Ensure `sala` exists before saving the arriendo
        SalaEntity sala = salaRepository.findById(arriendo.getSala().getId_sala())
                .orElseThrow(() -> new EntityNotFoundException("Sala not found"));

        // ✅ Ensure `cliente` exists before saving the arriendo
        ClienteEntity cliente = clienteRepository.findById(arriendo.getCliente().getId_cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found"));

        arriendo.setSala(sala);
        arriendo.setCliente(cliente);
        return arriendoRepository.save(arriendo);
    }

    /**
     * Update an existing arriendo.
     */
    @Transactional
    public ArriendoEntity update(Long id_arriendo, ArriendoEntity updatedArriendo) {
        return arriendoRepository.findById(id_arriendo).map(arriendo -> {
            SalaEntity sala = salaRepository.findById(updatedArriendo.getSala().getId_sala())
                    .orElseThrow(() -> new EntityNotFoundException("Sala not found"));

            ClienteEntity cliente = clienteRepository.findById(updatedArriendo.getCliente().getId_cliente())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente not found"));

            arriendo.setSala(sala);
            arriendo.setCliente(cliente);
            arriendo.setFecha(updatedArriendo.getFecha());
            arriendo.setHora_inicio(updatedArriendo.getHora_inicio());
            arriendo.setHora_fin(updatedArriendo.getHora_fin());
            arriendo.setEstado(updatedArriendo.getEstado());
            arriendo.setMonto_pagado(updatedArriendo.getMonto_pagado());

            return arriendoRepository.save(arriendo);
        }).orElseThrow(() -> new RuntimeException("Arriendo no encontrado con ID: " + id_arriendo));
    }

    /**
     * Delete an arriendo by ID.
     */
    @Transactional
    public void deleteById(Long id_arriendo) {
        if (!arriendoRepository.existsById(id_arriendo)) {
            throw new RuntimeException("Arriendo no encontrado con ID: " + id_arriendo);
        }
        arriendoRepository.deleteById(id_arriendo);
    }


    public List<ArriendoEntity> findByClienteId(Long idCliente) {
        return arriendoRepository.findByClienteId(idCliente);
    }



}
