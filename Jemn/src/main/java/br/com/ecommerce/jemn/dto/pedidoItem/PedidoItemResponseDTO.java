package br.com.ecommerce.jemn.dto.pedidoItem;

public class PedidoItemResponseDTO extends PedidoItemRequestDTO {
    
    private Long id;
    private double descontoItem;
    private double acrecimoItem;
    private double vltotalItem;

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
		this.acrecimoItem = 0;
	}

	public double getVltotalItem() {
		return vltotalItem;
	}

	public void setVltotalItem(double vltotalItem) {
		this.vltotalItem = vltotalItem;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
