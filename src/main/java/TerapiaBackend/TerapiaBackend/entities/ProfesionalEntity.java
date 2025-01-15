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
@Table(name = "profesional")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profesional;

    private int id_usuario; // ID de UsuarioEntity, relación manual

    private String especialidad;

    private String certificaciones;

    private String disponibilidad; // Ejemplo: "Lunes a Viernes 9:00-18:00"

    private String banco;

    private String nro_cuenta_bancaria;
}
