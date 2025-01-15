package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.CategoriaEntity;
import TerapiaBackend.TerapiaBackend.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaEntity> getAllCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CategoriaEntity> getCategoriaById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @PostMapping
    public CategoriaEntity createCategoria(@RequestBody CategoriaEntity categoria) {
        return categoriaService.save(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaEntity updateCategoria(@PathVariable Long id, @RequestBody CategoriaEntity categoria) {
        return categoriaService.update(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteById(id);
    }
}
