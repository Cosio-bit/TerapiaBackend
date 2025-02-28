package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.TerapiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerapiaRepository extends JpaRepository<TerapiaEntity, Long> {
    // No changes needed for now, but custom queries can be added later
}
