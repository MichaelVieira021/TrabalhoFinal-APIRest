package br.com.ecommerce.jemn.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @Column(nullable = false)
    private Date dtPedido;

    @Column(nullable = false)
    private double vltotalPedido;

    @Column
    private double descontoPedido;

    @Column
    private double acrescimoPedido;

    @Column
    private String obsPedido;

    @Column(nullable = false)
    private String formaPg;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoItem> pedidoItens;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
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

    public String getObsPedido() {
        return obsPedido;
    }

    public void setObsPedido(String obsPedido) {
        this.obsPedido = obsPedido;
    }
    
    public String getFormaPg() {
        return formaPg;
    }
    
    public void setFormaPg(String formaPg) {
        this.formaPg = formaPg;
    }
        
}
