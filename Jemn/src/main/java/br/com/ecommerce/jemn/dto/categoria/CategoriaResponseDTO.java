package br.com.ecommerce.jemn.dto.categoria;

import java.util.List;
import br.com.ecommerce.jemn.model.Produto;

public class CategoriaResponseDTO extends CategoriaRequestDTO {
    
    private Long id;
    private List<Produto> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
