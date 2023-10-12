package br.com.eccomerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eccomerce.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
    
}
