package TerapiaBackend.TerapiaBackend.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "terapia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerapiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_terapia;

    private String nombre;

    private String descripcion;

    private LocalDate fecha_inicio;

    private LocalDate fecha_fin;

    private int precio;

    private int cantidad_sesiones;

    private String estado; // Ejemplo: "activa", "completada", etc.
}
