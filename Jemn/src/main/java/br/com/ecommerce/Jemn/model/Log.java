package br.com.ecommerce.jemn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Log {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long Id;

    @Column
    private String tipo;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private double vlOriginal;

    @Column(nullable = false)
    private double vlAtual;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getVlOriginal() {
        return vlOriginal;
    }

    public void setVlOriginal(double vlOriginal) {
        this.vlOriginal = vlOriginal;
    }

    public double getVlAtual() {
        return vlAtual;
    }

    public void setVlAtual(double vlAtual) {
        this.vlAtual = vlAtual;
    }
    

}
