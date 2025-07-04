package com.perfulandiaSPA.cl.service;

import com.perfulandiaSPA.cl.repository.SucursalRepository;
import com.perfulandiaSPA.cl.model.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;
    
    public Sucursal findById(Integer id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }


    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void delete(Integer id) {
        sucursalRepository.deleteById(id);
    }
}
