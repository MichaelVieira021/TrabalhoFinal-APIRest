package br.com.ecommerce.jemn.dto.pedido;



public class PedidoResponseDTO extends PedidoRequestDTO{
    
    private Long id;
    private double vltotalPedido;  
    private double descontoPedido; 
    private double acrescimoPedido; 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	
}
