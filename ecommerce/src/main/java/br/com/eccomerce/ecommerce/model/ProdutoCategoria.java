package br.com.eccomerce.ecommerce.model;

public class ProdutoCategoria {
    
    private Long id;
    private String produtoCategoria;
    
    
    
    
    public ProdutoCategoria(Long id, String produtoCategoria) {
        this.id = id;
        this.produtoCategoria = produtoCategoria;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
   
    public String getProdutoCategoria() {
        return produtoCategoria;
    }
    
    public void setProdutoCategoria(String produtoCategoria) {
        this.produtoCategoria = produtoCategoria;
    }

    

}
