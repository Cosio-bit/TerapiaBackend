package TerapiaBackend.TerapiaBackend.repositories;

import TerapiaBackend.TerapiaBackend.entities.ProductoCompradoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductoCompradoRepository extends JpaRepository<ProductoCompradoEntity, Long> {

}
