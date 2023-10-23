package br.com.ecommerce.jemn.dto.pedidoItem;

import br.com.ecommerce.jemn.dto.produto.ProdutoResponseDTO;

public class PedidoItemBaseDTO {
    
    private Integer qtdPedidoitem;
    private ProdutoResponseDTO produto;
    private double descontoItem;
    private double acrecimoItem;
    private double vltotalItem;
    
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
    public double getDescontoItem() {
        return descontoItem;
    }
    public void setDescontoItem(double descontoItem) {
        this.descontoItem = descontoItem;
    }
    public double getAcrecimoItem() {
        return acrecimoItem;
    }
    public void setAcrecimoItem(double acrecimoItem) {
        this.acrecimoItem = acrecimoItem;
    }
    public double getVltotalItem() {
        return vltotalItem;
    }
    public void setVltotalItem(double vltotalItem) {
        this.vltotalItem = vltotalItem;
    }

    

    
}
