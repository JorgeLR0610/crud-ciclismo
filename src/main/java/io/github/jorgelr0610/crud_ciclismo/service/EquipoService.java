package io.github.jorgelr0610.crud_ciclismo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.jorgelr0610.crud_ciclismo.model.Equipo;
import io.github.jorgelr0610.crud_ciclismo.repository.EquipoRepository;
import jakarta.transaction.Transactional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository; //Constructor injection

    public EquipoService(EquipoRepository equipoRepository){
        this.equipoRepository = equipoRepository; //Constructor injection
    }

    public List<Equipo> findAll(){
        return equipoRepository.findAll();
    }

    public Equipo findById(Integer id){
        return equipoRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipo con ID: " + id + "no encontrado."));
    }

    @Transactional
    public Equipo create(Equipo equipo){
        return equipoRepository.save(equipo);
    }

    @Transactional
    public Equipo update(Integer id, Equipo datos){
        Equipo actual = findById(id);

        actual.setNoEquipo(datos.getNoEquipo());
        actual.setNombreEquipo(datos.getNombreEquipo());
        actual.setNombreDirTec(datos.getNombreDirTec());

        
        return equipoRepository.save(actual);
    }

    @Transactional
    public void delete(Integer id){
        equipoRepository.deleteById(id);
    }
}
