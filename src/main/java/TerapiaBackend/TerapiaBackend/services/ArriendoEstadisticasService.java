
package TerapiaBackend.TerapiaBackend.services;

import TerapiaBackend.TerapiaBackend.entities.ArriendoEntity;
import TerapiaBackend.TerapiaBackend.repositories.ArriendoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArriendoEstadisticasService {

    @Autowired
    private ArriendoRepository arriendoRepository;

    public Double getTotalMontoPagadoFiltrado(LocalDate startDate, LocalDate endDate, String estado, Long idCliente, Long idProveedor) {
        Double total = arriendoRepository.getTotalMontoPagadoFiltrado(startDate, endDate, estado, idCliente, idProveedor);
        return total != null ? total : 0.0;
    }

    public List<ArriendoEntity> getArriendosFiltrado(LocalDate startDate, LocalDate endDate, String estado, Long idCliente, Long idProveedor) {
        return arriendoRepository.findAllFiltrado(startDate, endDate, estado, idCliente, idProveedor);
    }

}
