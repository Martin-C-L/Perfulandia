package com.perfulandiaSPA.cl.Assembler;

import com.perfulandiaSPA.cl.controller.SucursalController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.perfulandiaSPA.cl.model.Sucursal;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Sucursal> toModel(Sucursal sucursal) {
        return EntityModel.of(sucursal,
            linkTo(methodOn(SucursalController.class).obtenerSucursalPorId(sucursal.getId())).withSelfRel(),
            linkTo(methodOn(SucursalController.class).listarSucursales()).withRel("sucursales")
        );
    }
}
