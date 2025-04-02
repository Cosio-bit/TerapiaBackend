package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.*;
import TerapiaBackend.TerapiaBackend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SesionGroupService {

    @Autowired
    private SesionGroupRepository sesionGroupRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private TerapiaRepository terapiaRepository;

    @Autowired
    private VarianteServicioRepository varianteServicioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<SesionGroupEntity> findAll() {
        return sesionGroupRepository.findAll();
    }

    public Optional<SesionGroupEntity> findById(Long id_sesion_group) {
        return sesionGroupRepository.findById(id_sesion_group);
    }

    @Transactional
    public SesionGroupEntity save(SesionGroupEntity sesionGroup) {
        // Ensure terapia, variante, and cliente exist
        TerapiaEntity terapia = terapiaRepository.findById(sesionGroup.getTerapia().getId_terapia())
                .orElseThrow(() -> new EntityNotFoundException("Terapia not found"));
        VarianteServicioEntity variante = varianteServicioRepository.findById(sesionGroup.getVariante().getId_variante())
                .orElseThrow(() -> new EntityNotFoundException("Variante not found"));
        ClienteEntity cliente = clienteRepository.findById(sesionGroup.getCliente().getId_cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found"));

        sesionGroup.setTerapia(terapia);
        sesionGroup.setVariante(variante);
        sesionGroup.setCliente(cliente);

        // ✅ Step 1: Save each session first so it gets an ID
        List<SesionEntity> savedSesiones = new ArrayList<>();
        for (SesionEntity sesion : sesionGroup.getSesiones()) {
            sesion.setId_sesion(null); // Ensure session is new
            SesionEntity savedSesion = sesionRepository.save(sesion); // Save session first
            savedSesiones.add(savedSesion);
        }

        // ✅ Step 2: Attach saved sessions to the group
        sesionGroup.setSesiones(savedSesiones);

        // ✅ Step 3: Save the session group with the now-persisted sessions
        return sesionGroupRepository.save(sesionGroup);
    }



    @Transactional
    public SesionGroupEntity update(Long id_sesion_group, SesionGroupEntity updatedSesionGroup) {
        return sesionGroupRepository.findById(id_sesion_group).map(sesionGroup -> {
            sesionGroup.setTerapia(updatedSesionGroup.getTerapia());
            sesionGroup.setCliente(updatedSesionGroup.getCliente());
            sesionGroup.setVariante(updatedSesionGroup.getVariante());
            sesionGroup.setDescripcion(updatedSesionGroup.getDescripcion());

            // ✅ Handle sessions update
            sesionGroup.getSesiones().clear(); // Remove old sessions
            for (SesionEntity sesion : updatedSesionGroup.getSesiones()) {
                sesion.setId_sesion(null); // Ensures new sessions get generated IDs
                sesionGroup.getSesiones().add(sesion);
            }

            return sesionGroupRepository.save(sesionGroup);
        }).orElseThrow(() -> new RuntimeException("Sesión de grupo no encontrada con ID: " + id_sesion_group));
    }

    @Transactional
    public void deleteById(Long id_sesion_group) {
        if (!sesionGroupRepository.existsById(id_sesion_group)) {
            throw new RuntimeException("Sesión de grupo no encontrada con ID: " + id_sesion_group);
        }
        sesionGroupRepository.deleteById(id_sesion_group);
    }

    public List<SesionGroupEntity> importarSesionGroups(List<SesionGroupEntity> sesionGroups) {
        return sesionGroupRepository.saveAll(sesionGroups);
    }


    public Double getTotalByFechaAndEstado(LocalDateTime startDate, LocalDateTime endDate, String estado) {
        Double total = sesionGroupRepository.getTotalByFechaAndEstado(startDate, endDate, estado);
        return total != null ? total : 0.0;
    }

    public List<SesionGroupEntity> getSesionesByFechaAndEstado(LocalDateTime startDate, LocalDateTime endDate, String estado) {
        return sesionGroupRepository.findAllByFechaAndEstado(startDate, endDate, estado);
    }

}
