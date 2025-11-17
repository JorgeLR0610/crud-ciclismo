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
@Table(name = "Maillots")
public class Maillot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, unique = true, length = 30)
    @NotBlank(message = "El tipo de maillot es obligatorio")
    @Size(min = 3, max = 30, message = "El tipo debe tener entre 3 y 30 caracteres")
    private String tipo;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "El color es obligatorio")
    @Size(min = 2, max = 20, message = "El color debe tener entre 2 y 20 caracteres")
    private String color;

    @Column(nullable = false, unique = false)
    @Min(value = 0, message = "El premio debe ser mayor o igual a 0")
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

