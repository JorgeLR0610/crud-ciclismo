package io.github.jorgelr0610.crud_ciclismo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.jorgelr0610.crud_ciclismo.model.Etapa;
import io.github.jorgelr0610.crud_ciclismo.repository.EtapaRepository;
import jakarta.transaction.Transactional;

@Service
public class EtapaService {

    private final EtapaRepository etapaRepository;

    public EtapaService(EtapaRepository etapaRepository){
        this.etapaRepository = etapaRepository;
    }

    public List<Etapa> findAll(){
        return etapaRepository.findAll();
    }

    public Etapa findById(Integer id){
        return etapaRepository.findById(id).orElseThrow(() -> new RuntimeException("Etapa con ID: " + id + " no encontrada."));
    }

    @Transactional
    public Etapa create(Etapa etapa){
        return etapaRepository.save(etapa);
    }

    @Transactional
    public Etapa update(Integer id, Etapa datos){
        Etapa actual = findById(id);

        actual.setNoEtapa(datos.getNoEtapa());
        actual.setKm(datos.getKm());
        actual.setSalida(datos.getSalida());
        actual.setLlegada(datos.getLlegada());
        actual.setPais(datos.getPais());

        return etapaRepository.save(actual);
    }

    @Transactional
    public void delete(Integer id){
        etapaRepository.deleteById(id);
    }
}

