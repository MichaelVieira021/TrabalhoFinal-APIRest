package br.com.ecommerce.jemn.dto.pedido;

import java.util.Date;
import java.util.List;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioResponseDTO;
import br.com.ecommerce.jemn.model.FormaPagamento;


public class PedidoRequestDTO {
     
    private String obsPedido;
    private FormaPagamento formaPg;
    private List<PedidoItemRequestDTO> pedidoItens;
    private Date dtPedido;
    private UsuarioResponseDTO usuario;
    
    public PedidoRequestDTO() {
    	this.dtPedido = new Date();
    }
    public Date getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}

	public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
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

	public List<PedidoItemRequestDTO> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<PedidoItemRequestDTO> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}
}
