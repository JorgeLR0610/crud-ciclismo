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

    // Método para buscar en función del campo que se seleccione
    public List<Concentrado> search(String campo, String query) {
        switch (campo) {
            case "idConcentrado":
                try {
                    int id = Integer.parseInt(query);
                    return concentradoRepository.findById(id)
                                .map(concentrado -> List.of(concentrado))
                                .orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "dorsal":
                try {
                    int dorsal = Integer.parseInt(query);
                    return concentradoRepository.findAll().stream()
                                .filter(c -> c.getDorsal() != null && c.getDorsal().getDorsal() == dorsal)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "noEtapa":
                try {
                    int noEtapa = Integer.parseInt(query);
                    return concentradoRepository.findAll().stream()
                                .filter(c -> c.getNoEtapa() != null && c.getNoEtapa().getNoEtapa() == noEtapa)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "codigo":
                try {
                    int codigo = Integer.parseInt(query);
                    return concentradoRepository.findAll().stream()
                                .filter(c -> c.getCodigo() != null && c.getCodigo().getCodigo() == codigo)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "idPuerto":
                try {
                    int idPuerto = Integer.parseInt(query);
                    return concentradoRepository.findAll().stream()
                                .filter(c -> c.getIdPuerto() != null && c.getIdPuerto().getIdPuerto() == idPuerto)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
                
            default:
                return List.of();
        }
    }
}

