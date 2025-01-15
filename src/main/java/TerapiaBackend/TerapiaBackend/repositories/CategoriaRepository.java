package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    // Métodos personalizados si se necesitan
}
