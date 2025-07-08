package com.perfulandiaSPA.cl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer clienteId;

    @ElementCollection
    @CollectionTable(name = "pedido_productos", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "producto_id")
    private List<Integer> productosIds;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String fechaCreacion;

    public void setSucursalId(int numberBetween) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSucursalId'");
    }

    public void setTotal(double randomDouble) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTotal'");
    }

    public void setFechaPedido(Date date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFechaPedido'");
    }
}
