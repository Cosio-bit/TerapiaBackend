package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.SesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SesionRepository extends JpaRepository<SesionEntity, Long> {

    @Query("SELECT SUM(s.precio) FROM SesionEntity s " +
            "WHERE s.fecha_hora BETWEEN :startDate AND :endDate " +
            "AND (:estado IS NULL OR s.estado = :estado) " +
            "AND (:idProfesional IS NULL OR s.profesional.id_profesional = :idProfesional)")
    Double getTotalSesionesFiltrado(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate,
                                    @Param("estado") String estado,
                                    @Param("idProfesional") Long idProfesional);

    @Query("SELECT s FROM SesionEntity s " +
            "WHERE s.fecha_hora BETWEEN :startDate AND :endDate " +
            "AND (:estado IS NULL OR s.estado = :estado) " +
            "AND (:idProfesional IS NULL OR s.profesional.id_profesional = :idProfesional)")
    List<SesionEntity> findSesionesFiltrado(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate,
                                            @Param("estado") String estado,
                                            @Param("idProfesional") Long idProfesional);

}
