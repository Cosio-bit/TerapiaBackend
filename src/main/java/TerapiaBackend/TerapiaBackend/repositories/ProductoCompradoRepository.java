package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.ProductoCompradoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoCompradoRepository extends JpaRepository<ProductoCompradoEntity, Long> {
    // Métodos personalizados si son necesarios
}
