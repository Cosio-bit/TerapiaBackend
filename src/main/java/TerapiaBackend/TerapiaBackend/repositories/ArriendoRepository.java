package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.ArriendoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArriendoRepository extends JpaRepository<ArriendoEntity, Long> {

    // Métodos personalizados, si los necesitas
    List<ArriendoEntity> findByEstado(String estado); // Buscar arriendos por estado
}
