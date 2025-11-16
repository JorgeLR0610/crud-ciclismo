package io.github.jorgelr0610.crud_ciclismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Etapas")
public class Etapa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noEtapa;

    @Column(nullable = false, unique = false)
    private int km;

    @Column(nullable = false, unique = false, length = 30)
    private String salida;

    @Column(nullable = false, unique = false, length = 30)
    private String llegada;

    @Column(nullable = false, unique = false, length = 20)
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
