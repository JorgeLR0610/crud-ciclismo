package io.github.jorgelr0610.crud_ciclismo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jorgelr0610.crud_ciclismo.model.Maillot;

public interface MaillotRepository extends JpaRepository<Maillot, Integer>{
    
    List<Maillot> findByTipoContaining(String tipo);
    
    List<Maillot> findByColorContaining(String color);
    
}
