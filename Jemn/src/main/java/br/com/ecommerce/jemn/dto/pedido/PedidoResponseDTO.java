package br.com.ecommerce.jemn.dto.pedido;

import java.util.Date;
import java.util.List;

import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemResponseDTO;

public class PedidoResponseDTO extends PedidoBaseDTO{
    
    private Long id;
    private double vltotalPedido;  
    private double descontoPedido; 
    private double acrescimoPedido;
	private Date dtPedido;
	private List<PedidoItemResponseDTO> pedidoItens;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public double getVltotalPedido() {
		return vltotalPedido;
	}

	public void setVltotalPedido(double vltotalPedido) {
		this.vltotalPedido = vltotalPedido;
	}

	public double getDescontoPedido() {
		return descontoPedido;
	}

	public void setDescontoPedido(double descontoPedido) {
		this.descontoPedido = descontoPedido;
	}

	public double getAcrescimoPedido() {
		return acrescimoPedido;
	}

	public void setAcrescimoPedido(double acrescimoPedido) {
		this.acrescimoPedido = acrescimoPedido;
	}

	public Date getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}

	public List<PedidoItemResponseDTO> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<PedidoItemResponseDTO> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}
}
