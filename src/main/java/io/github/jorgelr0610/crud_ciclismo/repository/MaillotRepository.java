package io.github.jorgelr0610.crud_ciclismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jorgelr0610.crud_ciclismo.model.Maillot;

public interface MaillotRepository extends JpaRepository<Maillot, Integer>{
    
}
