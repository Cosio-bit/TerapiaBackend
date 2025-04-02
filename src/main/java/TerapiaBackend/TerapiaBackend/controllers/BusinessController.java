package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.*;
import TerapiaBackend.TerapiaBackend.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("/amount")
    public Double getAmountBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return businessService.getAmountBetweenDates(startDate, endDate);
    }

    @GetMapping("/compras")
    public List<CompraEntity> getComprasBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return businessService.getComprasBetweenDates(startDate, endDate);
    }

    // Nuevos endpoints para Sesiones 👇

    @GetMapping("/gruposesiones/total")
    public Double getTotalSesionesByEstadoAndFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam String estado) {

        return businessService.getTotalSesionesByEstadoAndFecha(startDate, endDate, estado);
    }

    @GetMapping("/gruposesiones")
    public List<SesionGroupEntity> getSesionesByEstadoAndFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam String estado) {

        return businessService.getSesionesByEstadoAndFecha(startDate, endDate, estado);
    }


    // Nuevos endpoints para Arriendos:

    @GetMapping("/arriendos/total")
    public Double getTotalArriendosFiltrado(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long idCliente,
            @RequestParam(required = false) Long idProveedor) {

        return businessService.getTotalArriendosFiltrado(startDate, endDate, estado, idCliente, idProveedor);
    }

    @GetMapping("/arriendos")
    public List<ArriendoEntity> getArriendosFiltrado(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long idCliente,
            @RequestParam(required = false) Long idProveedor) {

        return businessService.getArriendosFiltrado(startDate, endDate, estado, idCliente, idProveedor);
    }



    @GetMapping("/sesiones/total")
    public Double getTotalSesionesIndividuales(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long idProfesional) {

        return businessService.getTotalSesionesFiltrado(startDate, endDate, estado, idProfesional);
    }

    @GetMapping("/sesiones")
    public List<SesionEntity> getSesionesIndividuales(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long idProfesional) {

        return businessService.getSesionesFiltrado(startDate, endDate, estado, idProfesional);
    }

    @GetMapping("/gastos/total")
    public Double getTotalGastosFiltrado(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long idProveedor) {

        return businessService.getTotalGastosFiltrado(startDate, endDate, nombre, idProveedor);
    }

    @GetMapping("/gastos")
    public List<GastoEntity> getGastosFiltrado(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long idProveedor) {

        return businessService.getGastosFiltrado(startDate, endDate, nombre, idProveedor);
    }

    @GetMapping("/resumen/ingresos")
    public Double getTotalIngresos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return businessService.calcularTotalIngresos(startDate, endDate);
    }

    @GetMapping("/resumen/gastos")
    public Double getTotalGastos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return businessService.calcularTotalGastos(startDate, endDate);
    }

    @GetMapping("/resumen/neto")
    public Double getGananciaNeta(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return businessService.calcularGananciaNeta(startDate, endDate);
    }


}
