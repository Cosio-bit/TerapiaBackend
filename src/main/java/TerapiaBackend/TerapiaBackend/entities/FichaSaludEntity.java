package TerapiaBackend.TerapiaBackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fichasalud")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaSaludEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fichasalud;

    private Date fecha;

    private String descripcion;

    private int id_cliente;

}
