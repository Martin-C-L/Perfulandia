package com.perfulandiaSPA.cl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer pedidoId;

    @Column(nullable = false)
    private String direccionDestino;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String fechaEnvio;

    @Column(nullable = false)
    private String transportista;
}
