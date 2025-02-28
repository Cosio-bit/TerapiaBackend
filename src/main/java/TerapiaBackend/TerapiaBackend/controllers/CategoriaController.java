package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.CategoriaEntity;
import TerapiaBackend.TerapiaBackend.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Obtener todas las categorías
    @GetMapping
    public List<CategoriaEntity> obtenerTodas() {
        return categoriaService.findAll();
    }

    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> obtenerPorId(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una categoría
    @PostMapping
    public ResponseEntity<CategoriaEntity> crear(@RequestBody CategoriaEntity categoria) {
        return ResponseEntity.ok(categoriaService.save(categoria));
    }

    // Actualizar una categoría
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEntity> actualizar(@PathVariable Long id, @RequestBody CategoriaEntity categoria) {
        return categoriaService.update(id, categoria)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Crear múltiples categorías
    @PostMapping("/importar")
    public List<CategoriaEntity> crearEnLote(@RequestBody List<CategoriaEntity> categorias) {
        return categoriaService.saveAll(categorias);
    }

    // Obtener terapias de una categoría específica
    @GetMapping("/{id}/terapias")
    public ResponseEntity<List<?>> obtenerTerapiasPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findTerapiasByCategoria(id));
    }

    // Obtener salas de una categoría específica
    @GetMapping("/{id}/salas")
    public ResponseEntity<List<?>> obtenerSalasPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findSalasByCategoria(id));
    }

    // Obtener productos de una categoría específica
    @GetMapping("/{id}/productos")
    public ResponseEntity<List<?>> obtenerProductosPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findProductosByCategoria(id));
    }
}
