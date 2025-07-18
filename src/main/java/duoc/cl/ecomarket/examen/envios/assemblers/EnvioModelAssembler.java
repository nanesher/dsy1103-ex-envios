package duoc.cl.ecomarket.examen.envios.assemblers;

import duoc.cl.ecomarket.examen.envios.controller.EnvioController;
import duoc.cl.ecomarket.examen.envios.model.Envio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {


    @Value("${usuarios-api-url}")
    private String usuariosApiUrl;

    @Override
    public EntityModel<Envio> toModel(Envio envio) {

        return EntityModel.of(envio,
                linkTo(methodOn(EnvioController.class).getById(envio.getEnvioId())).withSelfRel(),
                linkTo(methodOn(EnvioController.class).getAll()).withRel("Lista de todos los envios(con metodo get)"),
                linkTo(methodOn(EnvioController.class).delete(envio.getEnvioId())).withRel("Borrar el envio (con metodo delete)"),
                linkTo(methodOn(EnvioController.class).updateEnvio(envio.getEnvioId(),null)).withRel("Actualizar el envio(con metodo put y con body)"),
                Link.of(usuariosApiUrl + envio.getIdUsuario()).withRel("Usuario del envio"));
    }

}