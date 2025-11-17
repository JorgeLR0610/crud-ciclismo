package io.github.jorgelr0610.crud_ciclismo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jorgelr0610.crud_ciclismo.model.Puerto;

public interface PuertoRepository extends JpaRepository<Puerto, Integer>{
    
    List<Puerto> findByNombrePuertoContaining(String nombrePuerto);
    
}
