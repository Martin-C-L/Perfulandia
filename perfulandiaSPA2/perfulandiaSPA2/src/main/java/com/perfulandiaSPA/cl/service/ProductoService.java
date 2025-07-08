package com.perfulandiaSPA.cl.service;

import com.perfulandiaSPA.cl.model.Producto;
import com.perfulandiaSPA.cl.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
}
