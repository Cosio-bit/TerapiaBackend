package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProfesionalEntity;
import TerapiaBackend.TerapiaBackend.entities.TerapiaEntity;
import TerapiaBackend.TerapiaBackend.entities.VarianteServicioEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProfesionalRepository;
import TerapiaBackend.TerapiaBackend.repositories.TerapiaRepository;
import TerapiaBackend.TerapiaBackend.repositories.VarianteServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TerapiaService {

    @Autowired
    private TerapiaRepository terapiaRepository;

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private VarianteServicioRepository varianteServicioRepository;

    public List<TerapiaEntity> findAll() {
        return terapiaRepository.findAll();
    }

    public Optional<TerapiaEntity> findById(Long id) {
        return terapiaRepository.findById(id);
    }

    public TerapiaEntity createOrUpdateTerapia(TerapiaEntity terapia) {
        // Ensure mutable lists before saving
        if (terapia.getProfesionales() == null) {
            terapia.setProfesionales(new ArrayList<>());
        } else {
            terapia.setProfesionales(mergeProfesionales(terapia.getProfesionales()));
        }

        if (terapia.getVariantes() == null) {
            terapia.setVariantes(new ArrayList<>());
        } else {
            terapia.setVariantes(handleVariantes(terapia.getVariantes()));
        }

        return terapiaRepository.save(terapia);
    }

    public void deleteById(Long id) {
        if (!terapiaRepository.existsById(id)) {
            throw new RuntimeException("Terapia no encontrada con ID: " + id);
        }
        terapiaRepository.deleteById(id);
    }

    public List<TerapiaEntity> saveAll(List<TerapiaEntity> terapias) {
        terapias.forEach(terapia -> {
            terapia.setProfesionales(terapia.getProfesionales() != null ? mergeProfesionales(terapia.getProfesionales()) : new ArrayList<>());
            terapia.setVariantes(terapia.getVariantes() != null ? handleVariantes(terapia.getVariantes()) : new ArrayList<>());
        });

        return terapiaRepository.saveAll(terapias);
    }

    private List<ProfesionalEntity> mergeProfesionales(List<ProfesionalEntity> profesionales) {
        if (profesionales == null || profesionales.isEmpty()) {
            return new ArrayList<>();
        }
        return profesionales.stream()
                .map(prof -> profesionalRepository.findById(prof.getId_profesional())
                        .orElseThrow(() -> new RuntimeException("Profesional no encontrado con ID: " + prof.getId_profesional())))
                .toList();
    }

    private List<VarianteServicioEntity> handleVariantes(List<VarianteServicioEntity> variantes) {
        List<VarianteServicioEntity> processedVariantes = new ArrayList<>();
        for (VarianteServicioEntity variante : variantes) {
            if (variante.getId_variante() == null || variante.getId_variante() == 0) {
                // New variante, save it first
                VarianteServicioEntity newVariante = new VarianteServicioEntity();
                newVariante.setNombre(variante.getNombre());
                newVariante.setPrecio(variante.getPrecio());
                newVariante.setDuracion(variante.getDuracion());
                newVariante.setCantidad(variante.getCantidad());
                VarianteServicioEntity savedVariante = varianteServicioRepository.save(newVariante);
                processedVariantes.add(savedVariante);
            } else {
                // Existing variante, attach it
                processedVariantes.add(variante);
            }
        }
        return processedVariantes;
    }
}
