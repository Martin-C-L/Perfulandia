package com.perfulandiaSPA.cl.repository;

import com.perfulandiaSPA.cl.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {
    // Encuentra envíos por pedidoId
    List<Envio> findByPedidoId(Integer pedidoId);
    
    // Encuentra envíos por estado
    List<Envio> findByEstado(String estado);
    
    // Encuentra envíos por transportista
    List<Envio> findByTransportista(String transportista);
}
