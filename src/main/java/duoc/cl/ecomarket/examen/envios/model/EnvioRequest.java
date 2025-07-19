package duoc.cl.ecomarket.examen.envios.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioRequest {
    @Schema(description = "Identificador unico del usuario del envio", example = "6")
    private Integer idUsuario;
    @Schema(description = "Metodo de recepcion valido para la venta", example = "Retiro en tienda")
    private String tipoEntrega;

}
