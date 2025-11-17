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

    // Método para buscar en función del campo que se seleccione
    public List<Equipo> search(String campo, String query) {
        switch (campo) {
            case "nombreEquipo":
                // Necesitarías crear este método en tu EquipoRepository
                // (Spring Data JPA puede crear 'findByNombreContaining')
                return equipoRepository.findByNombreEquipoContaining(query); 

            case "nombreDirTec":
                // (Necesitarías crear 'findByMarcaContaining' en el repo)
                return equipoRepository.findByNombreDirTecContaining(query);

            case "noEquipo":
                try {
                    // El ID es una búsqueda exacta, no un "containing"
                    int id = Integer.parseInt(query);
                    // findById devuelve un Optional, lo manejamos
                    return equipoRepository.findById(id)
                                .map(equipo -> List.of(equipo)) // Lo convierte en lista
                                .orElse(List.of()); // Devuelve lista vacía si no lo encuentra
                } catch (NumberFormatException e) {
                    // Si el usuario no escribió un número para el ID
                    return List.of(); // Devuelve lista vacía
                }
                
            default:
                // Si el 'campo' no es uno esperado, no devuelve nada
                return List.of();
        }
    }
}
