package br.com.ecommerce.jemn.dto.pedido;

import java.util.List;
import br.com.ecommerce.jemn.model.FormaPagamento;
import br.com.ecommerce.jemn.model.PedidoItem;
import br.com.ecommerce.jemn.model.Usuario;

public class PedidoRequestDTO {
     
    private String obsPedido;
    private FormaPagamento formaPg;
    private List<PedidoItem> pedidoItens;
    private Usuario usuario;
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

	public List<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(List<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

}
