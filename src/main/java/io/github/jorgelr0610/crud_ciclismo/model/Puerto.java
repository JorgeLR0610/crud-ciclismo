package io.github.jorgelr0610.crud_ciclismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Puertos")
public class Puerto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuerto;

    @Column(nullable = false, unique = true, length = 30)
    private String nombrePuerto;

    @Column(nullable = false, unique = false)
    private int altura;

    @Column(nullable = false, unique = false)
    private int categoria;

    public Puerto() {
    }

    public Puerto(int idPuerto, String nombrePuerto, int altura, int categoria) {
        this.idPuerto = idPuerto;
        this.nombrePuerto = nombrePuerto;
        this.altura = altura;
        this.categoria = categoria;
    }

    public int getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(int idPuerto) {
        this.idPuerto = idPuerto;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    
}
