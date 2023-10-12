package br.com.eccomerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eccomerce.ecommerce.model.ProdutoCategoria;

public interface ProdutoCategoriaRepository extends JpaRepository <ProdutoCategoria, Long> {
    
}
