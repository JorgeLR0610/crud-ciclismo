package io.github.jorgelr0610.crud_ciclismo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Concentrado")
public class Concentrado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConcentrado;
    
    @ManyToOne
    @JoinColumn(name = "Dorsal")
    private Ciclista dorsal;

    @ManyToOne
    @JoinColumn(name = "No. de etapa")
    private Etapa noEtapa;

    @ManyToOne
    @JoinColumn(name = "CodigoMaillot")
    private Maillot codigo;

    @ManyToOne
    @JoinColumn(name = "ID del puerto")
    private Puerto idPuerto;

    public Concentrado() {
    }

    public Concentrado(int idConcentrado, Ciclista dorsal, Etapa noEtapa, Maillot codigo, Puerto idPuerto) {
        this.idConcentrado = idConcentrado;
        this.dorsal = dorsal;
        this.noEtapa = noEtapa;
        this.codigo = codigo;
        this.idPuerto = idPuerto;
    }

    public int getIdConcentrado() {
        return idConcentrado;
    }

    public void setIdConcentrado(int idConcentrado) {
        this.idConcentrado = idConcentrado;
    }

    public Ciclista getDorsal() {
        return dorsal;
    }

    public void setDorsal(Ciclista dorsal) {
        this.dorsal = dorsal;
    }

    public Etapa getNoEtapa() {
        return noEtapa;
    }

    public void setNoEtapa(Etapa noEtapa) {
        this.noEtapa = noEtapa;
    }

    public Maillot getCodigo() {
        return codigo;
    }

    public void setCodigo(Maillot codigo) {
        this.codigo = codigo;
    }

    public Puerto getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(Puerto idPuerto) {
        this.idPuerto = idPuerto;
    }

    
}
