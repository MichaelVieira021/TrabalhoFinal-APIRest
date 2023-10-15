package br.com.ecommerce.jemn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PedidoItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedidoitem")
    private Long id;

    @Column(nullable = false)
    private Integer qtdPedidoitem;

    @Column
    private double descontoItem;

    @Column
    private double acrecimoItem;

    @Column(nullable = false)
    private double vltotalItem;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    @JsonBackReference
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    @JsonBackReference
    private Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    
    
}
