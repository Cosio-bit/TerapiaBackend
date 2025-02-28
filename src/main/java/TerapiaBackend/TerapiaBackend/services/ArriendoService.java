
package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ArriendoEntity;
import TerapiaBackend.TerapiaBackend.repositories.ArriendoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArriendoService {

    @Autowired
    private ArriendoRepository arriendoRepository;

    // Obtener todos los arriendos
    public List<ArriendoEntity> findAll() {
        return arriendoRepository.findAll();
    }

    // Obtener arriendo por ID
    public Optional<ArriendoEntity> findById(Long id) {
        return arriendoRepository.findById(id);
    }

    // Crear o actualizar un arriendo
    public ArriendoEntity save(ArriendoEntity arriendo) {
        return arriendoRepository.save(arriendo);
    }

    // Actualizar arriendo existente
    public Optional<ArriendoEntity> update(Long id, ArriendoEntity arriendo) {
        return arriendoRepository.findById(id).map(existingArriendo -> {
            existingArriendo.setSala(arriendo.getSala());
            existingArriendo.setCliente(arriendo.getCliente());
            existingArriendo.setFecha(arriendo.getFecha());
            existingArriendo.setHora_inicio(arriendo.getHora_inicio());
            existingArriendo.setHora_fin(arriendo.getHora_fin());
            existingArriendo.setEstado(arriendo.getEstado());
            existingArriendo.setMonto_pagado(arriendo.getMonto_pagado());
            return arriendoRepository.save(existingArriendo);
        });
    }

    // Eliminar un arriendo por ID
    public boolean deleteById(Long id) {
        if (arriendoRepository.existsById(id)) {
            arriendoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener horarios disponibles para una sala en una fecha
    public List<Map<String, String>> getAvailableHours(Long idSala, LocalDate fecha) {
        LocalTime inicioDia = LocalTime.of(8, 0);  // Horario de inicio del día
        LocalTime finDia = LocalTime.of(20, 0);    // Horario de fin del día

        List<Object[]> horariosOcupados = arriendoRepository.findOccupiedHoursByDate(idSala, fecha);

        List<Map<String, String>> horariosDisponibles = new ArrayList<>();
        LocalTime inicioDisponible = inicioDia;

        for (Object[] horario : horariosOcupados) {
            LocalTime inicioOcupado = LocalTime.parse(horario[0].toString());
            LocalTime finOcupado = LocalTime.parse(horario[1].toString());

            if (inicioDisponible.isBefore(inicioOcupado)) {
                horariosDisponibles.add(Map.of(
                        "hora_inicio", inicioDisponible.toString(),
                        "hora_fin", inicioOcupado.toString()
                ));
            }
            inicioDisponible = finOcupado.isAfter(inicioDisponible) ? finOcupado : inicioDisponible;
        }

        if (inicioDisponible.isBefore(finDia)) {
            horariosDisponibles.add(Map.of(
                    "hora_inicio", inicioDisponible.toString(),
                    "hora_fin", finDia.toString()
            ));
        }

        return horariosDisponibles;
    }

    public List<ArriendoEntity> saveAll(List<ArriendoEntity> arriendos) {
        return arriendoRepository.saveAll(arriendos);
    }

}
