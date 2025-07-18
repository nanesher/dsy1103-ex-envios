package duoc.cl.ecomarket.examen.envios.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioRequest {
    private Integer idCliente;
    private String tipoEntrega;

}
