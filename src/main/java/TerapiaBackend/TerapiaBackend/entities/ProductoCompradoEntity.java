package TerapiaBackend.TerapiaBackend.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productocomprado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCompradoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_productocomprado;

    private String nombre;

    private BigDecimal precio;

    private int cantidad; // Cantidad comprada del producto

    private int id_producto; // Relación manual con ProductoEntity

    private int id_compra; // Relación manual con CompraEntity
}
