package br.com.eccomerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eccomerce.ecommerce.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
}
