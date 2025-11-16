package io.github.jorgelr0610.crud_ciclismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jorgelr0610.crud_ciclismo.model.Ciclista;

public interface CiclistaRepository extends JpaRepository<Ciclista, Integer>{
    
}
