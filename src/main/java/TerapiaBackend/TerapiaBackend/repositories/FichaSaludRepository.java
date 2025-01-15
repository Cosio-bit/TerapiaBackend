package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.FichaSaludEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaSaludRepository extends JpaRepository<FichaSaludEntity, Long> {
    // Métodos personalizados si se necesitan
}
