package duoc.cl.ecomarket.examen.envios.controller;


import duoc.cl.ecomarket.examen.envios.assemblers.EnvioModelAssembler;
import duoc.cl.ecomarket.examen.envios.model.Envio;
import duoc.cl.ecomarket.examen.envios.model.EnvioRequest;
import duoc.cl.ecomarket.examen.envios.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/envios")
@Tag(name="Envios",description = "Operaciones relacionadas con el registro de los envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private EnvioModelAssembler assembler;


    @Operation(summary="Obtener todos los envios",description = "Obtiene una lista de todos los envios con sus atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas los envios listados correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getAll() {
        List<EntityModel<Envio>> envios = envioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioController.class).getAll()).withSelfRel(),
                linkTo(methodOn(EnvioController.class).create(null)).withRel("Crear un envio(con metodo post)"));
    }

    @Operation(summary="Crear un envio",description = "Se crea un nuevo envio con los parametros enviados sin incluir el id asignadole una ID automaticamente" +
            " haciendo validaciones de sus datos para procesarlo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio creado con éxito",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody EnvioRequest envio1) {
        Envio envio = envioService.save(envio1);
        return ResponseEntity.ok(assembler.toModel(envio));
    }

    @Operation(summary="Obtener un envio",description = "Obtiene un envio con sus atributos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio listado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })

    @GetMapping (value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Envio> getById(@Parameter(
            description = "ID del envio a obtener",
            name = "id",
            required = true,
            in = ParameterIn.PATH,
            example = "5"
    )@PathVariable Integer id) {
        Envio envio = envioService.findById(id);
        return assembler.toModel(envio);
    }

    @Operation(summary="Eliminar un envio",description = "Se elimina un envio por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Envio eliminado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> delete(@Parameter(
            description = "ID del envio a eliminar",
            name = "id",
            required = true,
            in = ParameterIn.PATH,
            example = "5"
    )@PathVariable Integer id) {
        envioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary="Actualizar un envio",description = "Se actualizan los datos de un envio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio actualizado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Envio> updateEnvio(@Parameter(
            description = "ID del envio a actualizar",
            name = "id",
            required = true,
            in = ParameterIn.PATH,
            example = "5"
    )@PathVariable Integer id, @RequestBody Envio envio){
        Envio envioActualizada = envioService.update(id, envio);
        return assembler.toModel(envioActualizada);
    }

}



