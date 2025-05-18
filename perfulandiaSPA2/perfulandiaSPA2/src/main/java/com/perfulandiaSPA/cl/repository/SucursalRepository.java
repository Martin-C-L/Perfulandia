package com.perfulandiaSPA.cl.repository;

import com.perfulandiaSPA.cl.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
}