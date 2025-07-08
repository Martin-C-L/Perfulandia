package com.perfulandiaSPA.cl.controller;

import com.perfulandiaSPA.cl.Assembler.SucursalModelAssembler;
import com.perfulandiaSPA.cl.model.Sucursal;
import com.perfulandiaSPA.cl.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v2/sucursales", produces = MediaTypes.HAL_JSON_VALUE)
public class SucursalControllerV2 {
    
    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalModelAssembler assembler;

    @GetMapping
    public ResponseEntity<List<EntityModel<Sucursal>>> listarSucursales() {
        List<Sucursal> sucursales = sucursalService.findAll();
        if (sucursales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursales.stream()
                .map(sucursal -> assembler.toModel(sucursal))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Sucursal>> obtenerSucursalPorId(@PathVariable Integer id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(assembler.toModel(sucursal));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Sucursal>> agregarSucursal(@RequestBody Sucursal sucursal) {
        Sucursal nuevaSucursal = sucursalService.save(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(nuevaSucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Sucursal>> actualizarSucursal(@PathVariable Integer id, @RequestBody Sucursal sucursal) {
        Sucursal existente = sucursalService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        sucursal.setId(id);
        Sucursal actualizada = sucursalService.save(sucursal);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id) {
        Sucursal existente = sucursalService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

