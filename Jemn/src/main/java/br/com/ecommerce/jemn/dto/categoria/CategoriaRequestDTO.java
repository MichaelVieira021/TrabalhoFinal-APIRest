package br.com.ecommerce.jemn.dto.categoria;

public class CategoriaRequestDTO {

    private String nomeCategoria;
    private String obsCategoria;
    private boolean ativo;
    
    public CategoriaRequestDTO() {
		this.ativo = true;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    
}
