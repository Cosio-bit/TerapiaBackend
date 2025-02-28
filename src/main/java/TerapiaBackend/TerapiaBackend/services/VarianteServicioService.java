package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.VarianteServicioEntity;
import TerapiaBackend.TerapiaBackend.repositories.VarianteServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VarianteServicioService {

    @Autowired
    private VarianteServicioRepository varianteServicioRepository;

    public List<VarianteServicioEntity> findAll() {
        return varianteServicioRepository.findAll();
    }

    public VarianteServicioEntity save(VarianteServicioEntity varianteServicio) {
        return varianteServicioRepository.save(varianteServicio);
    }

    public VarianteServicioEntity findById(Long id_variante) {
        return varianteServicioRepository.findById(id_variante)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada con ID: " + id_variante));
    }

    public VarianteServicioEntity update(Long id_variante, VarianteServicioEntity varianteServicio) {
        VarianteServicioEntity existingVariante = findById(id_variante);
        existingVariante.setNombre(varianteServicio.getNombre());
        existingVariante.setPrecio(varianteServicio.getPrecio());
        existingVariante.setDuracion(varianteServicio.getDuracion());
        existingVariante.setCantidad(varianteServicio.getCantidad());
        return varianteServicioRepository.save(existingVariante);
    }

    public void deleteById(Long id_variante) {
        if (!varianteServicioRepository.existsById(id_variante)) {
            throw new RuntimeException("Variante no encontrada con ID: " + id_variante);
        }
        varianteServicioRepository.deleteById(id_variante);
    }

    public List<VarianteServicioEntity> saveAll(List<VarianteServicioEntity> variantes) {
        return varianteServicioRepository.saveAll(variantes);
    }

}
