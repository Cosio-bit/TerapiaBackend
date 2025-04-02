package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Long> {

    // Consulta que calcula el monto total entre fechas basado en productos comprados
    @Query("SELECT SUM(pc.producto.precio * pc.cantidad) FROM CompraEntity c JOIN c.productosComprados pc WHERE c.fecha BETWEEN :startDate AND :endDate")
    Double getAmountBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Obtiene todas las compras realizadas entre dos fechas
    List<CompraEntity> findAllByFechaBetween(LocalDateTime startDate, LocalDateTime endDate);


}
