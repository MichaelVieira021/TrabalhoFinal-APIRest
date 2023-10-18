package br.com.ecommerce.jemn.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Long id;

    @Column(nullable = false, length = 20)
    private String nomeProduto;

    @Column(nullable = true, length = 150)
    private String obsProduto;

    @Column(nullable = false)
    private double vlProduto;

    @Column(nullable = false)
    private Integer qtdProduto;

   
    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    @JsonBackReference
    private Categoria categoria;

    @OneToMany(mappedBy = "produto")
    private List<PedidoItem> pedidoItens;

     @Column(nullable = true ,columnDefinition = "TEXT")
    private String flieBase64;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(Double vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

    public String getFlieBase64() {
        return flieBase64;
    }

    public void setFlieBase64(String flieBase64) {
        this.flieBase64 = flieBase64;
    }
    
    
}

