package io.github.jorgelr0610.crud_ciclismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Maillots")
public class Maillot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, unique = true, length = 30)
    private String tipo;

    @Column(nullable = false, unique = true, length = 20)
    private String color;

    @Column(nullable = false, unique = false)
    private int premio;

    public Maillot() {
    }

    public Maillot(int codigo, String tipo, String color, int premio) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.color = color;
        this.premio = premio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    
}

