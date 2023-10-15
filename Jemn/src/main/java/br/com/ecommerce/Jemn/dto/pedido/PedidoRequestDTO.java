package br.com.ecommerce.jemn.dto.pedido;

import java.util.Date;
import java.util.List;
import br.com.ecommerce.jemn.model.PedidoItem;


public class PedidoRequestDTO {
    
    private double vltotalPedido;  
    private double descontoPedido; 
    private double acrescimoPedido;  
    private String obsPedido;
    private String formaPg;
    private List<PedidoItem> pedidoItens;
    private Long idUsuario;
    private Date dtPedido;
    
   

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

    public List<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(List<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

}
