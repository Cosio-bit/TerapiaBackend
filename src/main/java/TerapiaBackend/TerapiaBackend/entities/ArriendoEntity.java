package TerapiaBackend.TerapiaBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "arriendo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArriendoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_arriendo;

    @ManyToOne
    @JoinColumn(name = "id_sala", nullable = false)
    private SalaEntity sala;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    private LocalDate fecha;
    private String hora_inicio;
    private String hora_fin;
    private String estado;  // Example: "active", "completed", "canceled"
    private Double monto_pagado;  // Optional: Track how much the client paid
}

