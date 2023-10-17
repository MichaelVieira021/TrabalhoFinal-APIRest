package br.com.ecommerce.jemn.dto.pedidoItem;

import br.com.ecommerce.jemn.dto.pedido.PedidoRequestDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoRequestDTO;

public class PedidoItemRequestDTO {
    
    private Integer qtdPedidoitem;
    private ProdutoRequestDTO idProduto;
    private PedidoRequestDTO idPedido;

    public Integer getQtdPedidoitem() {
        return qtdPedidoitem;
    }

    public void setQtdPedidoitem(Integer qtdPedidoitem) {
        this.qtdPedidoitem = qtdPedidoitem;
    }

	public ProdutoRequestDTO getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(ProdutoRequestDTO idProduto) {
		this.idProduto = idProduto;
	}

	public PedidoRequestDTO getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(PedidoRequestDTO idPedido) {
		this.idPedido = idPedido;
	}  
}
