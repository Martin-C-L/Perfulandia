package com.perfulandiaSPA.cl.Assembler;

import com.perfulandiaSPA.cl.controller.PedidoController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.perfulandiaSPA.cl.model.Pedido;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
            linkTo(methodOn(PedidoController.class).obtenerPedidoPorId(pedido.getId())).withSelfRel(),
            linkTo(methodOn(PedidoController.class).listarPedidos()).withRel("pedidos")
        );
    }
}
