package io.github.jorgelr0610.crud_ciclismo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.jorgelr0610.crud_ciclismo.model.Maillot;
import io.github.jorgelr0610.crud_ciclismo.repository.MaillotRepository;
import jakarta.transaction.Transactional;

@Service
public class MaillotService {

    private final MaillotRepository maillotRepository;

    public MaillotService(MaillotRepository maillotRepository){
        this.maillotRepository = maillotRepository;
    }

    public List<Maillot> findAll(){
        return maillotRepository.findAll();
    }

    public Maillot findById(Integer id){
        return maillotRepository.findById(id).orElseThrow(() -> new RuntimeException("Maillot con ID: " + id + " no encontrado."));
    }

    @Transactional
    public Maillot create(Maillot maillot){
        return maillotRepository.save(maillot);
    }

    @Transactional
    public Maillot update(Integer id, Maillot datos){
        Maillot actual = findById(id);

        actual.setCodigo(datos.getCodigo());
        actual.setTipo(datos.getTipo());
        actual.setColor(datos.getColor());
        actual.setPremio(datos.getPremio());

        return maillotRepository.save(actual);
    }

    @Transactional
    public void delete(Integer id){
        maillotRepository.deleteById(id);
    }
}

