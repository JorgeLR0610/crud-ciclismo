package io.github.jorgelr0610.crud_ciclismo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jorgelr0610.crud_ciclismo.model.Ciclista;

public interface CiclistaRepository extends JpaRepository<Ciclista, Integer>{
    
    List<Ciclista> findByNombreContaining(String nombre);
    
    List<Ciclista> findByApContaining(String ap);
    
    List<Ciclista> findByAmContaining(String am);
    
}
