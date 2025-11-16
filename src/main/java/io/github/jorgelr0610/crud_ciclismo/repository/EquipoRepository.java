package io.github.jorgelr0610.crud_ciclismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jorgelr0610.crud_ciclismo.model.Equipo;
import io.github.jorgelr0610.crud_ciclismo.service.EquipoService;

public interface EquipoRepository extends JpaRepository<Equipo, Integer>{

}
