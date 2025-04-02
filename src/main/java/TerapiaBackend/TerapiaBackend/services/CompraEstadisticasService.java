package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.CompraEntity;
import TerapiaBackend.TerapiaBackend.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraEstadisticasService {

    @Autowired
    private CompraRepository compraRepository;

    public Double getAmountBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        Double amount = compraRepository.getAmountBetweenDates(startDate, endDate);
        return (amount != null) ? amount : 0.0; // Evita NullPointer si no hay compras
    }

    public List<CompraEntity> getComprasBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return compraRepository.findAllByFechaBetween(startDate, endDate);

    }
}
