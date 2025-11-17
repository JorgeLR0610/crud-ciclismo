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

    @SuppressWarnings("null")
    public Puerto findById(Integer id){
        return puertoRepository.findById(id).orElseThrow(() -> new RuntimeException("Puerto con ID: " + id + " no encontrado."));
    }

    @SuppressWarnings("null")
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

    @SuppressWarnings("null")
    @Transactional
    public void delete(Integer id){
        puertoRepository.deleteById(id);
    }

    // Método para buscar en función del campo que se seleccione
    public List<Puerto> search(String campo, String query) {
        switch (campo) {
            case "nombrePuerto":
                return puertoRepository.findByNombrePuertoContaining(query);
            
            case "idPuerto":
                try {
                    int id = Integer.parseInt(query);
                    return puertoRepository.findById(id)
                                .map(puerto -> List.of(puerto))
                                .orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "altura":
                try {
                    int altura = Integer.parseInt(query);
                    return puertoRepository.findAll().stream()
                                .filter(p -> p.getAltura() == altura)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "categoria":
                try {
                    int categoria = Integer.parseInt(query);
                    return puertoRepository.findAll().stream()
                                .filter(p -> p.getCategoria() == categoria)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
                
            default:
                return List.of();
        }
    }
}

