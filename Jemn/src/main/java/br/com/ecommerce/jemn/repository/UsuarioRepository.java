package br.com.ecommerce.jemn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.jemn.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);

}
