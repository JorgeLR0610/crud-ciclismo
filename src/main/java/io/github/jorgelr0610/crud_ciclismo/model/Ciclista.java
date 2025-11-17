package io.github.jorgelr0610.crud_ciclismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Ciclistas")
public class Ciclista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dorsal;

    @Column(nullable = false, unique = false, length = 20)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 20, message = "El nombre debe tener entre 2 y 20 caracteres")
    private String nombre;

    @Column(nullable = false, unique = false, length = 20)
    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(min = 2, max = 20, message = "El apellido paterno debe tener entre 2 y 20 caracteres")
    private String ap;

    @Column(nullable = false, unique = false, length = 20)
    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(min = 2, max = 20, message = "El apellido materno debe tener entre 2 y 20 caracteres")
    private String am;

    @Column(nullable = false, unique = false)
    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    private int edad;

    @ManyToOne // Define la relación: Muchos Estudiantes para Una Carrera.
    @JoinColumn(name = "noEquipo") // (Opcional pero recomendado) Nombrar la columna FK en la tabla 'Ciclista'
    @NotNull(message = "Debe seleccionar un equipo")
    private Equipo noEquipo; // El atributo es del TIPO de la otra entidad.

    public Ciclista() {
    }

    public Ciclista(int dorsal, String nombre, String ap, String am, int edad, Equipo noEquipo) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.ap = ap;
        this.am = am;
        this.edad = edad;
        this.noEquipo = noEquipo;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Equipo getNoEquipo() {
        return noEquipo;
    }

    public void setNoEquipo(Equipo noEquipo) {
        this.noEquipo = noEquipo;
    }
 
}
