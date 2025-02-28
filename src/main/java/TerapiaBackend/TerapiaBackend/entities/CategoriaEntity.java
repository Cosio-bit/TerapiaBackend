package TerapiaBackend.TerapiaBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    private String nombre;
    private String descripcion;

    @OneToMany
    @JoinColumn(name = "id_categoria")
    private List<TerapiaEntity> terapias;

    @OneToMany
    @JoinColumn(name = "id_categoria")
    private List<SalaEntity> salas;

    @OneToMany
    @JoinColumn(name = "id_categoria")
    private List<ProductoEntity> productos;
}
