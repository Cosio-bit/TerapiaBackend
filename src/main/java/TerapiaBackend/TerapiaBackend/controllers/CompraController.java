package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.CompraEntity;
import TerapiaBackend.TerapiaBackend.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<CompraEntity> getAllCompras() {
        return compraService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CompraEntity> getCompraById(@PathVariable Long id) {
        return compraService.findById(id);
    }

    @PostMapping
    public CompraEntity createCompra(@RequestBody CompraEntity compra) {
        return compraService.save(compra);
    }

    @PutMapping("/{id}")
    public CompraEntity updateCompra(@PathVariable Long id, @RequestBody CompraEntity compra) {
        return compraService.update(id, compra);
    }

    @DeleteMapping("/{id}")
    public void deleteCompra(@PathVariable Long id) {
        compraService.deleteById(id);
    }
}
