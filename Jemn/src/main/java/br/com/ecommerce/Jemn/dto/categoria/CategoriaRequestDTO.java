package br.com.ecommerce.jemn.dto.categoria;

public class CategoriaRequestDTO {

    private String nomeCategoria;
    private String obsCategoria;


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
