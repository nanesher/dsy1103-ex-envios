package duoc.cl.ecomarket.examen.envios.service;


import duoc.cl.ecomarket.examen.envios.model.Envio;
import duoc.cl.ecomarket.examen.envios.model.EnvioRequest;
import duoc.cl.ecomarket.examen.envios.model.UsuarioDTO;
import duoc.cl.ecomarket.examen.envios.repository.EnvioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EnvioService {
    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${usuarios-api-url}")
    private String usuariosApiUrl;

    public List<Envio> findAll(){
        List<Envio> envios = envioRepository.findAll();
        return envios;
    }
    public Envio findById(Integer id){
        Envio envio = envioRepository.findById(id).get();
        return envio;
    }
    public Envio save(EnvioRequest envioInicio){
        Envio envio = new Envio();
        String url = usuariosApiUrl + envioInicio.getIdUsuario();
        ResponseEntity<EntityModel<UsuarioDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assert response.getBody() != null;
        UsuarioDTO usuarioDTO = response.getBody().getContent();
        assert usuarioDTO != null;
        envio.setIdUsuario(usuarioDTO.getIdUsuario());
        envio.setNombreCliente(usuarioDTO.getNombre());
        envio.setTipoEntrega(envioInicio.getTipoEntrega());
        envio.setEstado("En proceso");
        if(envio.getTipoEntrega().equals("Retiro en tienda")){
            envio.setDireccionEntrega(" José Victorino Lastarria 307, Local 8, Santiago");
            envio.setFechaEntrega(LocalDate.now().plusDays(1));
        }else if(envio.getTipoEntrega().equals("Envio a domicilio")){
            envio.setDireccionEntrega(usuarioDTO.getDireccion());
            envio.setFechaEntrega(LocalDate.now().plusDays(3));
        }
        return envioRepository.save(envio);

    }
    public void deleteById(Integer id){
        envioRepository.deleteById(id);
    }
    public Envio update(Integer id, Envio envio2){
        Envio envio1 = findById(id);
        envio1.setEstado(envio2.getEstado());
        envio1.setFechaEntrega(envio2.getFechaEntrega());
        envio1.setDireccionEntrega(envio2.getDireccionEntrega());
        envio1.setTipoEntrega(envio2.getTipoEntrega());
        envio1.setFechaSolicitud(envio2.getFechaSolicitud());
        envio1.setNombreCliente(envio2.getNombreCliente());
        return envioRepository.save(envio1);

    }
}
