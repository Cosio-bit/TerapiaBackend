package TerapiaBackend.TerapiaBackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sala")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sala;

    private String nombre;

    private int capacidad;

    private int precio;

    private String ubicacion;

    private String estado; // Ejemplo: "disponible", "mantenimiento", etc.

    private int id_proveedor;
}

