package TerapiaBackend.TerapiaBackend.controllers;

import TerapiaBackend.TerapiaBackend.entities.ProfesionalEntity;
import TerapiaBackend.TerapiaBackend.services.ProfesionalEstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales-estadisticas")
public class ProfesionalEstadisticasController {

    @Autowired
    private ProfesionalEstadisticasService profesionalEstadisticasService;


}
