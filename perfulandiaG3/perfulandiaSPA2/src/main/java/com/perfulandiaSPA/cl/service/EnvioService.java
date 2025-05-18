package com.perfulandiaSPA.cl.service;

import com.perfulandiaSPA.cl.model.Envio;
import com.perfulandiaSPA.cl.repository.EnvioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    public Envio findById(Integer id) {
        Optional<Envio> envio = envioRepository.findById(id);
        return envio.orElse(null);
    }

    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    public void delete(Integer id) {
        envioRepository.deleteById(id);
    }
}
