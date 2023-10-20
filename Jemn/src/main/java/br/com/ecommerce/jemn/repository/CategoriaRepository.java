package br.com.ecommerce.jemn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.jemn.model.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long> {}