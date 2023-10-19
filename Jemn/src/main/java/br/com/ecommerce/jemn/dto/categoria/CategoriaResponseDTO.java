package br.com.ecommerce.jemn.dto.categoria;

public class CategoriaResponseDTO extends CategoriaRequestDTO {
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
