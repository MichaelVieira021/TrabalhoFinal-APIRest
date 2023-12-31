package br.com.ecommerce.jemn.dto.log;

import br.com.ecommerce.jemn.model.Usuario;

public class LogRequestDTO {
    
    private String tipo; //ENUM
    private double vlOriginal;
    private double vlAtual;
    private Usuario usuario;
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
