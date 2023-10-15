package br.com.ecommerce.jemn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.jemn.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
    

}
