package io.github.jorgelr0610.crud_ciclismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Etapas")
public class Etapa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noEtapa;

    @Column(nullable = false, unique = false)
    @Min(value = 1, message = "Los kilómetros deben ser mayor a 0")
    private int km;

    @Column(nullable = false, unique = false, length = 30)
    @NotBlank(message = "La ciudad de salida es obligatoria")
    @Size(min = 2, max = 30, message = "La ciudad de salida debe tener entre 2 y 30 caracteres")
    private String salida;

    @Column(nullable = false, unique = false, length = 30)
    @NotBlank(message = "La ciudad de llegada es obligatoria")
    @Size(min = 2, max = 30, message = "La ciudad de llegada debe tener entre 2 y 30 caracteres")
    private String llegada;

    @Column(nullable = false, unique = false, length = 20)
    @NotBlank(message = "El país es obligatorio")
    @Size(min = 2, max = 20, message = "El país debe tener entre 2 y 20 caracteres")
    private String pais;

    public Etapa() {
    }

    public Etapa(int noEtapa, int km, String salida, String llegada, String pais) {
        this.noEtapa = noEtapa;
        this.km = km;
        this.salida = salida;
        this.llegada = llegada;
        this.pais = pais;
    }

    public int getNoEtapa() {
        return noEtapa;
    }

    public void setNoEtapa(int noEtapa) {
        this.noEtapa = noEtapa;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getLlegada() {
        return llegada;
    }

    public void setLlegada(String llegada) {
        this.llegada = llegada;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
}
