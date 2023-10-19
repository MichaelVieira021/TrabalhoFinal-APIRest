package br.com.ecommerce.jemn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.jemn.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);

}
