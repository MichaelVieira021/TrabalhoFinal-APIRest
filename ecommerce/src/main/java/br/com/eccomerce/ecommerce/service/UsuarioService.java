package br.com.eccomerce.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eccomerce.ecommerce.model.Usuario;
import br.com.eccomerce.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
    

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario obterPorId (Long id){
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if (optUsuario.isEmpty()) {
            throw new RuntimeException("Nenhum usuario encontrado para o ID: " + id);
            
        }

        return optUsuario.get();
    }

    public Usuario adicionar(Usuario usuario){
        usuario.setId(null);
        return usuarioRepository.save(usuario);
    }


    public Usuario atualizar(long id, Usuario usuario){

        obterPorId(id);

        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deletar(long id){
        obterPorId(id);

        usuarioRepository.deleteById(id);
    }
}
