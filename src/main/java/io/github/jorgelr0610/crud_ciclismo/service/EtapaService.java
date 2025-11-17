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

    @SuppressWarnings("null")
    public Etapa findById(Integer id){
        return etapaRepository.findById(id).orElseThrow(() -> new RuntimeException("Etapa con ID: " + id + " no encontrada."));
    }

    @SuppressWarnings("null")
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

    @SuppressWarnings("null")
    @Transactional
    public void delete(Integer id){
        etapaRepository.deleteById(id);
    }

    // Método para buscar en función del campo que se seleccione
    public List<Etapa> search(String campo, String query) {
        switch (campo) {
            case "salida":
                return etapaRepository.findBySalidaContaining(query);
            
            case "llegada":
                return etapaRepository.findByLlegadaContaining(query);
            
            case "pais":
                return etapaRepository.findByPaisContaining(query);
            
            case "noEtapa":
                try {
                    int id = Integer.parseInt(query);
                    return etapaRepository.findById(id)
                                .map(etapa -> List.of(etapa))
                                .orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "km":
                try {
                    int km = Integer.parseInt(query);
                    return etapaRepository.findAll().stream()
                                .filter(e -> e.getKm() == km)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
                
            default:
                return List.of();
        }
    }
}

