package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.FichaSaludEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FichaSaludRepository extends JpaRepository<FichaSaludEntity, Long> {

    // Contar fichas de salud en un rango de fechas
    long countByFechaBetween(Date fechaInicio, Date fechaFin);
}
