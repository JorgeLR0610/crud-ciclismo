package io.github.jorgelr0610.crud_ciclismo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.jorgelr0610.crud_ciclismo.model.Concentrado;
import io.github.jorgelr0610.crud_ciclismo.repository.ConcentradoRepository;
import jakarta.transaction.Transactional;

@Service
public class ConcentradoService {

    private final ConcentradoRepository concentradoRepository;

    public ConcentradoService(ConcentradoRepository concentradoRepository){
        this.concentradoRepository = concentradoRepository;
    }

    public List<Concentrado> findAll(){
        return concentradoRepository.findAll();
    }

    public Concentrado findById(Integer id){
        return concentradoRepository.findById(id).orElseThrow(() -> new RuntimeException("Concentrado con ID: " + id + " no encontrado."));
    }

    @Transactional
    public Concentrado create(Concentrado concentrado){
        return concentradoRepository.save(concentrado);
    }

    @Transactional
    public Concentrado update(Integer id, Concentrado datos){
        Concentrado actual = findById(id);

        actual.setIdConcentrado(datos.getIdConcentrado());
        actual.setDorsal(datos.getDorsal());
        actual.setNoEtapa(datos.getNoEtapa());
        actual.setCodigo(datos.getCodigo());
        actual.setIdPuerto(datos.getIdPuerto());

        return concentradoRepository.save(actual);
    }

    @Transactional
    public void delete(Integer id){
        concentradoRepository.deleteById(id);
    }
}

