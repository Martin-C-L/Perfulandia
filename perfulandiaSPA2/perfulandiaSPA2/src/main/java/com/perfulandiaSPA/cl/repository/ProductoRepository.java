package com.perfulandiaSPA.cl.repository;

import com.perfulandiaSPA.cl.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Encuentra productos por nombre
    List<Producto> findByNombre(String nombre);
    
    // Encuentra productos por categoría
    List<Producto> findByCategoria(String categoria);
    
    // Encuentra productos con stock menor a un valor
    List<Producto> findByStockLessThan(int stock);
    
    // Encuentra productos con precio menor a un valor
    List<Producto> findByPrecioLessThan(double precio);
}
