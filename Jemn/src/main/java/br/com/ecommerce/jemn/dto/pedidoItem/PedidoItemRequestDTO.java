package br.com.ecommerce.jemn.dto.pedidoItem;

import br.com.ecommerce.jemn.model.Pedido;
import br.com.ecommerce.jemn.model.Produto;

public class PedidoItemRequestDTO {
    
    private Integer qtdPedidoitem;
    private Produto idProduto;
    private Pedido idPedido;

    public Integer getQtdPedidoitem() {
        return qtdPedidoitem;
    }

    public void setQtdPedidoitem(Integer qtdPedidoitem) {
        this.qtdPedidoitem = qtdPedidoitem;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }  
   
}
