package br.com.ecommerce.jemn.dto.pedidoItem;

public class PedidoItemResponseDTO extends PedidoItemRequestDTO {
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
