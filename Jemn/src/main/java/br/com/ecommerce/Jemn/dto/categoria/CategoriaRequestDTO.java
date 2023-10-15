package br.com.ecommerce.jemn.dto.categoria;

import java.util.List;

import br.com.ecommerce.jemn.model.Produto;

public class CategoriaRequestDTO {

    private String nomeCategoria;
    private String obsCategoria;
     private List<Produto> produtos;



    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getObsCategoria() {
        return obsCategoria;
    }

    public void setObsCategoria(String obsCategoria) {
        this.obsCategoria = obsCategoria;
    }
    
}
