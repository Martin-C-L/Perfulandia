package com.perfulandiaSPA.cl.controller;

import com.perfulandiaSPA.cl.model.Envio;
import com.perfulandiaSPA.cl.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/enviosssss")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listarEnvios() {
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(envios); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvioPorId(@PathVariable Integer id) {
        Envio envio = envioService.findById(id);
        if (envio == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(envio); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Envio> agregarEnvio(@RequestBody Envio envio) {
        Envio nuevoEnvio = envioService.save(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
        Envio existente = envioService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        envio.setId(id);
        Envio actualizado = envioService.save(envio);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Integer id) {
        Envio existente = envioService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        envioService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
