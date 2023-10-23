package br.com.ecommerce.jemn.dto.pedido;

import java.util.List;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemRequestDTO;

public class PedidoRequestDTO extends PedidoBaseDTO{

    private List<PedidoItemRequestDTO> pedidoItens;

	public List<PedidoItemRequestDTO> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<PedidoItemRequestDTO> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}
}
