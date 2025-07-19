package duoc.cl.ecomarket.examen.envios.repository;

import duoc.cl.ecomarket.examen.envios.model.Envio;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EnvioRepository EnvioRepository;
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            Envio envio = new Envio();
            envio.setIdUsuario(faker.number().numberBetween(1,15));
            String[] estados = {"En proceso", "En transito", "Entregado"};
            String estado = estados[random.nextInt(estados.length)];
            envio.setEstado(estado);
            envio.setFechaSolicitud(LocalDateTime.now());
            envio.setFechaEntrega(LocalDate.now().plusDays(1));
            String[] tipos = {"Retiro en tienda", "Envio a domicilio"};
            String tipo = tipos[random.nextInt(tipos.length)];
            envio.setTipoEntrega(tipo);
            envio.setNombreCliente(faker.name().fullName());
            envio.setDireccionEntrega(faker.address().fullAddress());
            EnvioRepository.save(envio);
        }
    }
}

