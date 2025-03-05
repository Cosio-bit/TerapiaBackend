package TerapiaBackend.TerapiaBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sala")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sala;

    private String nombre;
    private int capacidad;
    private int precio;
    private String ubicacion;
    private String estado;  // Example: "available", "maintenance", etc.

    @ManyToOne(fetch = FetchType.LAZY)  // 👈 Same as `cliente` in SesionGroupEntity
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity proveedor;
}
