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
    
   //#region propriedades
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLog")
    private Long Id;

    @Column
    private ETipoEntidade tipo;

    @Column(nullable = false)
    private String acao;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private String vlOriginal;

    @Column(nullable = false)
    private String vlAtual;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonBackReference
    private Usuario usuario;
    
    //#endregion

    public Log(ETipoEntidade tipo, String acao,String vlOriginal, String vlAtual,Usuario usuario) {
        this.tipo = tipo;
        this.acao = acao;
        this.data = new Date();
        this.vlOriginal = vlOriginal;
        this.vlAtual = vlAtual;
        this.usuario = usuario;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ETipoEntidade getTipo() {
        return tipo;
    }

    public void setTipo(ETipoEntidade tipo) {
        this.tipo = tipo;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }
   
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getVlOriginal() {
        return vlOriginal;
    }

    public void setVlOriginal(String vlOriginal) {
        this.vlOriginal = vlOriginal;
    }

    public String getVlAtual() {
        return vlAtual;
    }

    public void setVlAtual(String vlAtual) {
        this.vlAtual = vlAtual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
