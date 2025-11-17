package io.github.jorgelr0610.crud_ciclismo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.jorgelr0610.crud_ciclismo.model.Ciclista;
import io.github.jorgelr0610.crud_ciclismo.repository.CiclistaRepository;
import jakarta.transaction.Transactional;

@Service
public class CiclistaService {

    private final CiclistaRepository ciclistaRepository;

    public CiclistaService(CiclistaRepository ciclistaRepository){
        this.ciclistaRepository = ciclistaRepository;
    }

    public List<Ciclista> findAll(){
        return ciclistaRepository.findAll();
    }

    public Ciclista findById(Integer id){
        return ciclistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ciclista con ID: " + id + " no encontrado."));
    }

    @Transactional
    public Ciclista create(Ciclista ciclista){
        return ciclistaRepository.save(ciclista);
    }

    @Transactional
    public Ciclista update(Integer id, Ciclista datos){
        Ciclista actual = findById(id);

        actual.setDorsal(datos.getDorsal());
        actual.setNombre(datos.getNombre());
        actual.setAp(datos.getAp());
        actual.setAm(datos.getAm());
        actual.setEdad(datos.getEdad());
        actual.setNoEquipo(datos.getNoEquipo());

        return ciclistaRepository.save(actual);
    }

    @Transactional
    public void delete(Integer id){
        ciclistaRepository.deleteById(id);
    }

    // Método para buscar en función del campo que se seleccione
    public List<Ciclista> search(String campo, String query) {
        switch (campo) {
            case "nombre":
                return ciclistaRepository.findByNombreContaining(query);
            
            case "ap":
                return ciclistaRepository.findByApContaining(query);
            
            case "am":
                return ciclistaRepository.findByAmContaining(query);
            
            case "dorsal":
                try {
                    int id = Integer.parseInt(query);
                    return ciclistaRepository.findById(id)
                                .map(ciclista -> List.of(ciclista))
                                .orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "edad":
                try {
                    int edad = Integer.parseInt(query);
                    return ciclistaRepository.findAll().stream()
                                .filter(c -> c.getEdad() == edad)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
                
            default:
                return List.of();
        }
    }
}

