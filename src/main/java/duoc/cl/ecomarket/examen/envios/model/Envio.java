package duoc.cl.ecomarket.examen.envios.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="envios")
public class Envio {

    @Schema(description = "Identificador unico del envio", example = "3")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="envio_id")
    private Integer envioId;

    @Schema(description = "Fecha en la que se entregará el producto",example = "2025-07-20")
    @Column(name="fecha_entrega")
    private LocalDate fechaEntrega;

    @Schema(description = "Fecha en la que se solicitó el envío",example = "2025-07-18T14:30:00")
    @Column(name="fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Schema(description = "Direccion en donde se realizará la entrega del producto",example = "Calle Wallaby 42")
    @Column(name="direccion_entrega")
    private String direccionEntrega;

    @Schema(description = "Forma en la que se entregara el pedido", example="Retiro en tienda")
    @Column(name="tipo_entrega")
    private String tipoEntrega;

    @Schema(description = "Estado en el que se encuentra la compra",example = "En proceso")
    @Column(name="estado")
    private String estado;

    @Schema(description = "Nombre del ciente que realizó la compra", example = "Cristiano Ronaldo")
    @Column(name="nombre_cliente")
    private String nombreCliente;

    @Schema(description = "Identificador unico del usuario de la compra", example = "4")
    @Column(name="id_usuario")
    private Integer idUsuario;

    @PrePersist
    protected void onCreate() {
        this.fechaSolicitud = LocalDateTime.now();
    }

}
