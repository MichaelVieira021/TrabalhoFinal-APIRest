package br.com.ecommerce.jemn.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Column(name = "idPedido")
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
    //@Enumerated(EnumType.STRING)
    private FormaPagamento formaPg;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoItem> pedidoItens;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonBackReference
    private Usuario usuario;
    
    public Pedido() {
    	this.dtPedido = new Date();
    }

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

	public FormaPagamento getFormaPg() {
		return formaPg;
	}

	public void setFormaPg(FormaPagamento formaPg) {
		this.formaPg = formaPg;
	}

	public List<PedidoItem> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<PedidoItem> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}     
}
