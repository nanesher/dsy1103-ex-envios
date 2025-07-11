package duoc.cl.ecomarket.examen.envios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnviosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnviosApplication.class, args);
	}
	@Value("${productos-api-url}")
	private String productosApiUrl;
	@Value("${ventas-api-url}")
	private String ventasApiUrl;
	@Value("${usuarios-api-url}")
	private String usuariosApiUrl;
	@Value("${pagos-api-url}")
	private String pagossApiUrl;


}
