package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.GastoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<GastoEntity, Long> {

    // Obtener todas las instancias de un gasto por nombre en un rango de fechas
    @Query("SELECT g FROM GastoEntity g WHERE g.nombre = :nombre AND g.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<GastoEntity> findGastosByNombreAndFecha(
            @Param("nombre") String nombre,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

    // Calcular el promedio de los gastos por nombre en un rango de fechas
    @Query("SELECT AVG(g.monto) FROM GastoEntity g WHERE g.nombre = :nombre AND g.fecha BETWEEN :fechaInicio AND :fechaFin")
    Double findAverageGastoByNombreAndFecha(
            @Param("nombre") String nombre,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

    // Total de gastos en un rango de fechas
    @Query("SELECT SUM(g.monto) FROM GastoEntity g WHERE g.fecha BETWEEN :fechaInicio AND :fechaFin")
    Double findTotalGastosByFecha(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );
}
