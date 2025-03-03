package TerapiaBackend.TerapiaBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @ManyToOne(fetch = FetchType.LAZY)  // 👈 Same as `cliente` in SesionGroupEntity
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity proveedor;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
}
