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

    // Método para buscar en función del campo que se seleccione
    public List<Maillot> search(String campo, String query) {
        switch (campo) {
            case "tipo":
                return maillotRepository.findByTipoContaining(query);
            
            case "color":
                return maillotRepository.findByColorContaining(query);
            
            case "codigo":
                try {
                    int id = Integer.parseInt(query);
                    return maillotRepository.findById(id)
                                .map(maillot -> List.of(maillot))
                                .orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }
            
            case "premio":
                try {
                    int premio = Integer.parseInt(query);
                    return maillotRepository.findAll().stream()
                                .filter(m -> m.getPremio() == premio)
                                .toList();
                } catch (NumberFormatException e) {
                    return List.of();
                }
                
            default:
                return List.of();
        }
    }
}

