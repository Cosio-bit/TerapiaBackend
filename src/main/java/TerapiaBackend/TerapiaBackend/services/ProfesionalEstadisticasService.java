package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ProfesionalEntity;
import TerapiaBackend.TerapiaBackend.repositories.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalEstadisticasService {

    @Autowired
    private ProfesionalRepository profesionalRepository;


}
