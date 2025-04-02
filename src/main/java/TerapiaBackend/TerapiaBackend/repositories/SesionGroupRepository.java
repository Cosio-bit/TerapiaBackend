package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.SesionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SesionGroupRepository extends JpaRepository<SesionGroupEntity, Long> {

    // Obtener el monto total filtrando por rango de fechas y estado de la sesión
    @Query("SELECT SUM(s.precio) FROM SesionGroupEntity sg JOIN sg.sesiones s " +
            "WHERE s.fecha_hora BETWEEN :startDate AND :endDate AND s.estado = :estado")
    Double getTotalByFechaAndEstado(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate,
                                    @Param("estado") String estado);

    // Obtener todas las SesionGroupEntity que tengan sesiones dentro del rango y estado específico
    @Query("SELECT DISTINCT sg FROM SesionGroupEntity sg JOIN sg.sesiones s " +
            "WHERE s.fecha_hora BETWEEN :startDate AND :endDate AND s.estado = :estado")
    List<SesionGroupEntity> findAllByFechaAndEstado(@Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate,
                                                    @Param("estado") String estado);

}
