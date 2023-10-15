package br.com.ecommerce.jemn.dto.pedidoItem;

public class PedidoItemRequestDTO {
    
    private Integer qtdPedidoitem;
    private double descontoItem;
    private double acrecimoItem;
    private double vltotalItem;
    private Long idProduto;
    private Long idPedido;
    

    public Integer getQtdPedidoitem() {
        return qtdPedidoitem;
    }

    public void setQtdPedidoitem(Integer qtdPedidoitem) {
        this.qtdPedidoitem = qtdPedidoitem;
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

     public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

   
}
