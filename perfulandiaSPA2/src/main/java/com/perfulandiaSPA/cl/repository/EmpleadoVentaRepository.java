package com.perfulandiaSPA.cl.repository;

import com.perfulandiaSPA.cl.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoVentaRepository extends JpaRepository<Pedido, Integer> {
    // Encuentra pedidos por clienteId
    List<Pedido> findByClienteId(Integer clienteId);
    
    // Encuentra pedidos por estado
    List<Pedido> findByEstado(String estado);
    
    // Encuentra pedidos por fecha de creación
    List<Pedido> findByFechaCreacion(String fechaCreacion);
}
