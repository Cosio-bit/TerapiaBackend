package TerapiaBackend.TerapiaBackend.entities;

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
@Table(name = "arriendo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArriendoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_arriendo;

    private LocalDate fecha_inicio;

    private LocalDate fecha_termino;

    private String estado; // Ejemplo: "activo", "finalizado", "cancelado"

    private int id_sala;
}

