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

    // Obtener todas las categorías
    public List<CategoriaEntity> findAll() {
        return categoriaRepository.findAll();
    }

    // Obtener una categoría por ID
    public Optional<CategoriaEntity> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    // Crear una nueva categoría
    public CategoriaEntity save(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }

    // Actualizar una categoría existente
    public Optional<CategoriaEntity> update(Long id, CategoriaEntity updatedCategoria) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(updatedCategoria.getNombre());
            categoria.setDescripcion(updatedCategoria.getDescripcion());
            categoria.setTerapias(updatedCategoria.getTerapias());
            categoria.setSalas(updatedCategoria.getSalas());
            categoria.setProductos(updatedCategoria.getProductos());
            return categoriaRepository.save(categoria);
        });
    }

    // Eliminar una categoría
    public boolean deleteById(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Crear múltiples categorías
    public List<CategoriaEntity> saveAll(List<CategoriaEntity> categorias) {
        return categoriaRepository.saveAll(categorias);
    }

    // Obtener terapias asociadas a una categoría
    public List<?> findTerapiasByCategoria(Long id) {
        return categoriaRepository.findById(id)
                .map(CategoriaEntity::getTerapias)
                .orElse(List.of());
    }

    // Obtener salas asociadas a una categoría
    public List<?> findSalasByCategoria(Long id) {
        return categoriaRepository.findById(id)
                .map(CategoriaEntity::getSalas)
                .orElse(List.of());
    }

    // Obtener productos asociados a una categoría
    public List<?> findProductosByCategoria(Long id) {
        return categoriaRepository.findById(id)
                .map(CategoriaEntity::getProductos)
                .orElse(List.of());
    }
}
