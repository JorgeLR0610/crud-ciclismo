package io.github.jorgelr0610.crud_ciclismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jorgelr0610.crud_ciclismo.model.Equipo;
import java.util.List;


public interface EquipoRepository extends JpaRepository<Equipo, Integer>{

    List<Equipo> findByNombreEquipoContaining(String nombreEquipo);

    List<Equipo> findByNombreDirTecContaining(String nombreDirTec);

}
