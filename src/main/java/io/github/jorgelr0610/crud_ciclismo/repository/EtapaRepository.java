package io.github.jorgelr0610.crud_ciclismo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jorgelr0610.crud_ciclismo.model.Etapa;

public interface EtapaRepository extends JpaRepository<Etapa, Integer>{
    
    List<Etapa> findBySalidaContaining(String salida);
    
    List<Etapa> findByLlegadaContaining(String llegada);
    
    List<Etapa> findByPaisContaining(String pais);
    
}
