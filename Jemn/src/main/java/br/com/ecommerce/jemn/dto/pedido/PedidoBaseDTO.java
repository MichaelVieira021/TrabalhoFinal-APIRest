package br.com.ecommerce.jemn.dto.pedido;

import br.com.ecommerce.jemn.dto.usuario.UsuarioResponseDTO;
import br.com.ecommerce.jemn.model.FormaPagamento;

public class PedidoBaseDTO {
    private String obsPedido;
    private FormaPagamento formaPg;
    private UsuarioResponseDTO usuario;

    public String getObsPedido() {
        return obsPedido;
    }

    public void setObsPedido(String obsPedido) {
        this.obsPedido = obsPedido;
    }

    public FormaPagamento getFormaPg() {
        return formaPg;
    }

    public void setFormaPg(FormaPagamento formaPg) {
        this.formaPg = formaPg;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }
    
    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    } 
}
