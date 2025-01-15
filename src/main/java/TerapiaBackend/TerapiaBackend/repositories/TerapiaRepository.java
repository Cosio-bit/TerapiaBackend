package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.TerapiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerapiaRepository extends JpaRepository<TerapiaEntity, Long> {
    // Métodos personalizados si son necesarios
}
