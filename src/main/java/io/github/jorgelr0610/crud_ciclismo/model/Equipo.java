package io.github.jorgelr0610.crud_ciclismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Equipos")
public class Equipo {
    
    @Id //Esta anotación marca el atributo como PK
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //Identity es para que genere id's autoincrementales
    private int noEquipo;

    @Column(nullable =  false, unique = true, length = 30)
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
    private String nombreEquipo;

    @Column(nullable = false, unique = true, length = 50)
    private String nombreDirTec;

    public Equipo() {
    }

    public Equipo(int noEquipo, String nombreEquipo, String nombreDirTec) {
        this.noEquipo = noEquipo;
        this.nombreEquipo = nombreEquipo;
        this.nombreDirTec = nombreDirTec;
    }

    public int getNoEquipo() {
        return noEquipo;
    }

    public void setNoEquipo(int noEquipo) {
        this.noEquipo = noEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreDirTec() {
        return nombreDirTec;
    }

    public void setNombreDirTec(String nombreDirTec) {
        this.nombreDirTec = nombreDirTec;
    }

}
