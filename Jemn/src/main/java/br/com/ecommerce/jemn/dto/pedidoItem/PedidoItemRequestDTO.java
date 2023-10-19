package br.com.ecommerce.jemn.dto.pedidoItem;

import br.com.ecommerce.jemn.dto.pedido.PedidoResponseDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoResponseDTO;

public class PedidoItemRequestDTO {
    
    private Integer qtdPedidoitem;
    private ProdutoResponseDTO produto;
    private PedidoResponseDTO pedido;
    
    public Integer getQtdPedidoitem() {
        return qtdPedidoitem;
    }

    public void setQtdPedidoitem(Integer qtdPedidoitem) {
        this.qtdPedidoitem = qtdPedidoitem;
    }

	public ProdutoResponseDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoResponseDTO produto) {
		this.produto = produto;
	}

	public PedidoResponseDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoResponseDTO pedido) {
		this.pedido = pedido;
	}
}
