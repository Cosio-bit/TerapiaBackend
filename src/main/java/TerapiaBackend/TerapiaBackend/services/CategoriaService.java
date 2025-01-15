package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.CategoriaEntity;
import TerapiaBackend.TerapiaBackend.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaEntity> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<CategoriaEntity> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public CategoriaEntity save(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public CategoriaEntity update(Long id, CategoriaEntity updatedCategoria) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(updatedCategoria.getNombre());
            categoria.setDescripcion(updatedCategoria.getDescripcion());
            categoria.setActiva(updatedCategoria.getActiva());
            categoria.setOrden(updatedCategoria.getOrden());
            return categoriaRepository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }
}
