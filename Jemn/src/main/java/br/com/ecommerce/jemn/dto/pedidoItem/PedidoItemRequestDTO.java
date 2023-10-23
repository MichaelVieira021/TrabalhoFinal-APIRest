package br.com.ecommerce.jemn.dto.pedidoItem;

import br.com.ecommerce.jemn.dto.pedido.PedidoResponseDTO;

public class PedidoItemRequestDTO extends PedidoItemBaseDTO{
    
    private PedidoResponseDTO pedido;

	public PedidoResponseDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoResponseDTO pedido) {
		this.pedido = pedido;
	}
}
