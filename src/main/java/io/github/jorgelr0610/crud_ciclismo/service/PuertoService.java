package io.github.jorgelr0610.crud_ciclismo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.jorgelr0610.crud_ciclismo.model.Puerto;
import io.github.jorgelr0610.crud_ciclismo.repository.PuertoRepository;
import jakarta.transaction.Transactional;

@Service
public class PuertoService {

    private final PuertoRepository puertoRepository;

    public PuertoService(PuertoRepository puertoRepository){
        this.puertoRepository = puertoRepository;
    }

    public List<Puerto> findAll(){
        return puertoRepository.findAll();
    }

    public Puerto findById(Integer id){
        return puertoRepository.findById(id).orElseThrow(() -> new RuntimeException("Puerto con ID: " + id + " no encontrado."));
    }

    @Transactional
    public Puerto create(Puerto puerto){
        return puertoRepository.save(puerto);
    }

    @Transactional
    public Puerto update(Integer id, Puerto datos){
        Puerto actual = findById(id);

        actual.setIdPuerto(datos.getIdPuerto());
        actual.setNombrePuerto(datos.getNombrePuerto());
        actual.setAltura(datos.getAltura());
        actual.setCategoria(datos.getCategoria());

        return puertoRepository.save(actual);
    }

    @Transactional
    public void delete(Integer id){
        puertoRepository.deleteById(id);
    }
}

