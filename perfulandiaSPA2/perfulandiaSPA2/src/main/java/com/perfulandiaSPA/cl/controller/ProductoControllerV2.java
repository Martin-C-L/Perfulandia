package com.perfulandiaSPA.cl.controller;

import com.perfulandiaSPA.cl.Assembler.ProductoModelAssembler;
import com.perfulandiaSPA.cl.model.Producto;
import com.perfulandiaSPA.cl.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getAllProductos() {
        List<EntityModel<Producto>> productos = productoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoControllerV2.class).getAllProductos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Producto> getProductoById(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }
        return assembler.toModel(producto);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> createProducto(@RequestBody Producto producto) {
        Producto nuevo = productoService.save(producto);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoControllerV2.class).getProductoById(nuevo.getId())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        if (productoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        Producto actualizado = productoService.save(producto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
        if (productoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        productoService.delete(id);
        return ResponseEntity.noContent().build();}
}
