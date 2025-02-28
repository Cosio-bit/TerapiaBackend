
package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.ArriendoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArriendoRepository extends JpaRepository<ArriendoEntity, Long> {

    @Query("SELECT a.hora_inicio, a.hora_fin FROM ArriendoEntity a WHERE a.sala.id_sala = :idSala AND a.fecha = :fecha ORDER BY a.hora_inicio ASC")
    List<Object[]> findOccupiedHoursByDate(@Param("idSala") Long idSala, @Param("fecha") LocalDate fecha);

    @Query("SELECT COUNT(a.id_arriendo), SUM(a.monto_pagado) FROM ArriendoEntity a WHERE a.sala.id_sala = :idSala AND a.fecha BETWEEN :fechaInicio AND :fechaFin")
    Object[] findArriendosAndGananciasBySala(@Param("idSala") Long idSala, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT a.fecha, a.hora_inicio, a.hora_fin, s.nombre, s.ubicacion FROM ArriendoEntity a JOIN a.sala s WHERE a.cliente.id_cliente = :idCliente AND a.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Object[]> findSalasArrendadasByCliente(@Param("idCliente") Long idCliente, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT SUM(a.monto_pagado) FROM ArriendoEntity a WHERE a.cliente.id_cliente = :idCliente AND a.fecha BETWEEN :fechaInicio AND :fechaFin")
    Double findGastoTotalByCliente(@Param("idCliente") Long idCliente, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
}
