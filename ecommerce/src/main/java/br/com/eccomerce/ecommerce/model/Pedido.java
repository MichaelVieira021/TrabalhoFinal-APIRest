package br.com.eccomerce.ecommerce.model;

import java.util.Date;
import java.util.List;

public class Pedido {
    
    private Long id;
    private String numeroPedido;
    private Date dataPedido;
    private double valorTotal;
    private double descontoTotal;
    private double acrescimoTotal;
    private String observacao;
    private List <Usuario> clientes;

    public Pedido(Long id, String numeroPedido, Date dataPedido, double valorTotal, double descontoTotal,
            double acrescimoTotal, String observacao, List<Usuario> clientes) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.descontoTotal = descontoTotal;
        this.acrescimoTotal = acrescimoTotal;
        this.observacao = observacao;
        this.clientes = clientes;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNumeroPedido() {
        return numeroPedido;
    }


    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }


    public Date getDataPedido() {
        return dataPedido;
    }


    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }


    public double getValorTotal() {
        return valorTotal;
    }


    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    public double getDescontoTotal() {
        return descontoTotal;
    }


    public void setDescontoTotal(double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }


    public double getAcrescimoTotal() {
        return acrescimoTotal;
    }


    public void setAcrescimoTotal(double acrescimoTotal) {
        this.acrescimoTotal = acrescimoTotal;
    }


    public String getObservacao() {
        return observacao;
    }


    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    public List<Usuario> getClientes() {
        return clientes;
    }


    public void setClientes(List<Usuario> clientes) {
        this.clientes = clientes;
    }

    
}
