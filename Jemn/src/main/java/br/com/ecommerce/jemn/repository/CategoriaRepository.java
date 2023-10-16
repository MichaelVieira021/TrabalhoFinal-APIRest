package br.com.ecommerce.jemn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.jemn.model.Categoria;
import java.util.List;


public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
    
}
