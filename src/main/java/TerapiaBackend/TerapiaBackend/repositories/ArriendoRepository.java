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

    @Query("SELECT a FROM ArriendoEntity a WHERE a.cliente.id_cliente = :idCliente")
    List<ArriendoEntity> findByClienteId(@Param("idCliente") Long idCliente);


    // Monto total filtrando por fechas, estado, cliente y proveedor
    @Query("SELECT SUM(a.monto_pagado) FROM ArriendoEntity a " +
            "WHERE a.fecha BETWEEN :startDate AND :endDate " +
            "AND (:estado IS NULL OR a.estado = :estado) " +
            "AND (:idCliente IS NULL OR a.cliente.id_cliente = :idCliente) " +
            "AND (:idProveedor IS NULL OR a.sala.proveedor.id_proveedor = :idProveedor)")
    Double getTotalMontoPagadoFiltrado(@Param("startDate") LocalDate startDate,
                                       @Param("endDate") LocalDate endDate,
                                       @Param("estado") String estado,
                                       @Param("idCliente") Long idCliente,
                                       @Param("idProveedor") Long idProveedor);

    // Obtener arriendos filtrando por fechas, estado, cliente y proveedor
    @Query("SELECT a FROM ArriendoEntity a " +
            "WHERE a.fecha BETWEEN :startDate AND :endDate " +
            "AND (:estado IS NULL OR a.estado = :estado) " +
            "AND (:idCliente IS NULL OR a.cliente.id_cliente = :idCliente) " +
            "AND (:idProveedor IS NULL OR a.sala.proveedor.id_proveedor = :idProveedor)")
    List<ArriendoEntity> findAllFiltrado(@Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate,
                                         @Param("estado") String estado,
                                         @Param("idCliente") Long idCliente,
                                         @Param("idProveedor") Long idProveedor);
}
