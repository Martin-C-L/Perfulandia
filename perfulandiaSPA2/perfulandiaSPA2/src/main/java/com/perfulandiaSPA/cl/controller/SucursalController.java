package com.perfulandiaSPA.cl.controller;

import com.perfulandiaSPA.cl.model.Sucursal;
import com.perfulandiaSPA.cl.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> listarSucursales() {
        List<Sucursal> sucursales = sucursalService.findAll();
        if (sucursales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursalPorId(@PathVariable Integer id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursal);
    }

    @PostMapping
    public ResponseEntity<Sucursal> agregarSucursal(@RequestBody Sucursal sucursal) {
        Sucursal nuevaSucursal = sucursalService.save(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizarSucursal(@PathVariable Integer id, @RequestBody Sucursal sucursal) {
        Sucursal existente = sucursalService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        sucursal.setId(id);
        Sucursal actualizada = sucursalService.save(sucursal);
        return ResponseEntity.ok(actualizada);
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