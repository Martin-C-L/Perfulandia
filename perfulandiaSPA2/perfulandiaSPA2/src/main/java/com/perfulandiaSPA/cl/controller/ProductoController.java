package com.perfulandiaSPA.cl.controller;

import com.perfulandiaSPA.cl.model.Producto;
import com.perfulandiaSPA.cl.model.Sucursal;
import com.perfulandiaSPA.cl.service.ProductoService;
import com.perfulandiaSPA.cl.service.SucursalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        if (producto.getSucursal() == null || producto.getSucursal().getId() == null) {
        return ResponseEntity.badRequest().build();
    }

        Sucursal sucursal = sucursalService.findById(producto.getSucursal().getId());
        if (sucursal == null) {
        return ResponseEntity.badRequest().build();
    }

        producto.setSucursal(sucursal);
        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
}



    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto existente = productoService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        Producto actualizado = productoService.save(producto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        Producto existente = productoService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
