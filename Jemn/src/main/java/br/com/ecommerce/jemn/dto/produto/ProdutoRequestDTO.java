package br.com.ecommerce.jemn.dto.produto;

import br.com.ecommerce.jemn.dto.categoria.CategoriaResponseDTO;

public class ProdutoRequestDTO {
	
    private String nomeProduto;
    private String obsProduto;
    private double vlProduto;
    private Integer qtdProduto;
    private CategoriaResponseDTO categoria;
    private boolean ativo;

    public ProdutoRequestDTO() {
		this.ativo = true;
	}

	public CategoriaResponseDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponseDTO idCategoria) {
        this.categoria = idCategoria;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getObsProduto() {
        return obsProduto;
    }

    public void setObsProduto(String obsProduto) {
        this.obsProduto = obsProduto;
    }

    public double getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(double vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
