package TerapiaBackend.TerapiaBackend.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import TerapiaBackend.TerapiaBackend.controllers.SesionEstadisticasController;
import TerapiaBackend.TerapiaBackend.entities.*;


import TerapiaBackend.TerapiaBackend.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private CompraEstadisticasService compraService;

    @Autowired
    private SesionGroupService sesionGroupService;

    @Autowired
    private ArriendoEstadisticasService arriendoService;

    @Autowired
    private SesionEstadisticasService sesionService;

    @Autowired
    private GastoEstadisticasService gastoService;

    //we are going to get the beetwen of dates and the total of the amount
    public Double getAmountBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        Double totalAmount = 0.0;
        totalAmount += compraService.getAmountBetweenDates(startDate, endDate);
        return totalAmount;
    }

        //we are going to get the entities between of dates
    public List<CompraEntity> getComprasBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return compraService.getComprasBetweenDates(startDate, endDate);
    }


    // Nuevos métodos para SesionGroupEntity 👇

    // Obtiene monto total por sesiones según estado y rango de fechas
    public Double getTotalSesionesByEstadoAndFecha(LocalDateTime startDate, LocalDateTime endDate, String estado) {
        return sesionGroupService.getTotalByFechaAndEstado(startDate, endDate, estado);
    }

    // Obtiene las entidades SesionGroup filtradas según estado y fechas
    public List<SesionGroupEntity> getSesionesByEstadoAndFecha(LocalDateTime startDate, LocalDateTime endDate, String estado) {
        return sesionGroupService.getSesionesByFechaAndEstado(startDate, endDate, estado);
    }

    // Nuevos métodos para ArriendoEntity
    public Double getTotalArriendosFiltrado(LocalDate startDate, LocalDate endDate, String estado, Long idCliente, Long idProveedor) {
        return arriendoService.getTotalMontoPagadoFiltrado(startDate, endDate, estado, idCliente, idProveedor);
    }

    public List<ArriendoEntity> getArriendosFiltrado(LocalDate startDate, LocalDate endDate, String estado, Long idCliente, Long idProveedor) {
        return arriendoService.getArriendosFiltrado(startDate, endDate, estado, idCliente, idProveedor);
    }


// Métodos nuevos para Sesiones individuales:

    public Double getTotalSesionesFiltrado(LocalDateTime startDate, LocalDateTime endDate, String estado, Long idProfesional) {
        return sesionService.getTotalSesionesFiltrado(startDate, endDate, estado, idProfesional);
    }

    public List<SesionEntity> getSesionesFiltrado(LocalDateTime startDate, LocalDateTime endDate, String estado, Long idProfesional) {
        return sesionService.getSesionesFiltrado(startDate, endDate, estado, idProfesional);
    }


    // Métodos para gastos filtrados
    public Double getTotalGastosFiltrado(LocalDate startDate, LocalDate endDate, String nombre, Long idProveedor) {
        return gastoService.getTotalGastosFiltrado(startDate, endDate, nombre, idProveedor);
    }

    public List<GastoEntity> getGastosFiltrado(LocalDate startDate, LocalDate endDate, String nombre, Long idProveedor) {
        return gastoService.getGastosFiltrado(startDate, endDate, nombre, idProveedor);
    }

    // Mtodo que calcula el total de ingresos en un periodo (compras, arriendos, sesiones individuales, sesiones grupales)
    public Double calcularTotalIngresos(LocalDate startDate, LocalDate endDate) {
        double ingresosCompras = getAmountBetweenDates(startDate.atStartOfDay(), endDate.atTime(23,59,59));
        double ingresosArriendos = getTotalArriendosFiltrado(startDate, endDate, null, null, null);
        double ingresosSesionesGrupalesRealizado = getTotalSesionesByEstadoAndFecha(startDate.atStartOfDay(), endDate.atTime(23,59,59), "Pagado y Realizado");
        double ingresosSesionesGrupalesNoRealizado = getTotalSesionesByEstadoAndFecha(startDate.atStartOfDay(), endDate.atTime(23,59,59), "Pagado y No Realizado");

        double ingresosSesionesGrupales = ingresosSesionesGrupalesRealizado + ingresosSesionesGrupalesNoRealizado;

        return ingresosCompras + ingresosArriendos + ingresosSesionesGrupales;
    }

    // Mtodo que obtiene el total de gastos en un periodo
    public Double calcularTotalGastos(LocalDate startDate, LocalDate endDate) {
        return getTotalGastosFiltrado(startDate, endDate, null, null);
    }

    // Mtodo final para calcular ganancias netas (ingresos - gastos)
    public Double calcularGananciaNeta(LocalDate startDate, LocalDate endDate) {
        Double ingresos = calcularTotalIngresos(startDate, endDate);
        Double gastos = calcularTotalGastos(startDate, endDate);
        return ingresos - gastos;
    }



}
