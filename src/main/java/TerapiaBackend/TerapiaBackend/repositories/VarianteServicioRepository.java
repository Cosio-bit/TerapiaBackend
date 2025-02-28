package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.VarianteServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarianteServicioRepository extends JpaRepository<VarianteServicioEntity, Long> {
    // No changes needed for now, but custom queries can be added later
}
