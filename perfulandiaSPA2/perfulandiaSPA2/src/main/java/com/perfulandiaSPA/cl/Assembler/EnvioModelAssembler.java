package com.perfulandiaSPA.cl.Assembler;

import com.perfulandiaSPA.cl.controller.EnvioController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.perfulandiaSPA.cl.model.Envio;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(envio,
            linkTo(methodOn(EnvioController.class).obtenerEnvioPorId(envio.getId())).withSelfRel(),
            linkTo(methodOn(EnvioController.class).listarEnvios()).withRel("envios")
        );
    }
}

