package br.com.ecommerce.jemn.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.ecommerce.jemn.dto.usuario.UsuarioLoginResponseDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioRequestDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioResponseDTO;
import br.com.ecommerce.jemn.model.Usuario;
import br.com.ecommerce.jemn.model.exceptions.ResourceConflict;
import br.com.ecommerce.jemn.repository.UsuarioRepository;
import br.com.ecommerce.jemn.security.JWTService;

@Service
public class UsuarioService {

	private static final String BEARER = "Bearer ";
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
    private JWTService jwtService;

	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<UsuarioResponseDTO> obterTodos(){
		List<Usuario> usuarios = usuarioRepository.findAll();

		return usuarios
			.stream()
			.map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public UsuarioResponseDTO obterPorId(Long id){
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		
		if(optUsuario.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
	}
	
	public UsuarioResponseDTO adicionar(UsuarioRequestDTO usuarioRequest){
		Usuario usuarioModel = mapper.map(usuarioRequest, Usuario.class);
        String senha =  passwordEncoder.encode(usuarioModel.getSenha());
		usuarioModel.setSenha(senha);
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioRequest){
		obterPorId(id);
		Usuario usuarioModel = mapper.map(usuarioRequest, Usuario.class);
		usuarioModel.setId(id);
		String senha =  passwordEncoder.encode(usuarioModel.getSenha());
		usuarioModel.setSenha(senha);
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		usuarioRepository.deleteById(id);
	}

	public UsuarioResponseDTO obterPorEmail(String email){
        Optional<Usuario> optUsuario =  usuarioRepository.findByEmail(email);
        
		if(optUsuario.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o email: " + email);
        }
		
        return mapper.map(optUsuario.get(),UsuarioResponseDTO.class);
    }

    public UsuarioLoginResponseDTO logar(String email, String senha){
        Authentication autenticacao = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(email, senha,Collections.emptyList()));
            
        SecurityContextHolder.getContext().setAuthentication(autenticacao);
        String token =  BEARER + jwtService.gerarToken(autenticacao);
        UsuarioResponseDTO usuarioResponse = obterPorEmail(email);
        
        return new UsuarioLoginResponseDTO(token, usuarioResponse);
    }
    
	public void uniqueEMAILeTEL(UsuarioRequestDTO usuarioRequest, Long id){
		List<UsuarioResponseDTO> listaUsuarioResponse = obterTodos();

		for (UsuarioResponseDTO usuarioResponse : listaUsuarioResponse){
			if(usuarioResponse.getEmail().equals(usuarioRequest.getEmail()) && usuarioResponse.getId() != id){
				throw new ResourceConflict("E-mail já cadastrado!");
			}else if (usuarioResponse.getTelefone().equals(usuarioResponse.getTelefone()) && usuarioResponse.getId() != id) {
				throw new ResourceConflict("Telefone já cadastrado!");
			}
		}
	}
}
