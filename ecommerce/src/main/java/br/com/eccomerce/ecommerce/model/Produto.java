package br.com.eccomerce.ecommerce.model;


public class Produto {
    
    private Long id;
    private String produto;
    
    
    
    public Produto(Long id, String produto) {
        this.id = id;
        this.produto = produto;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProduto() {
        return produto;
    }
    
    public void setProduto(String produto) {
        this.produto = produto;
    }


}
