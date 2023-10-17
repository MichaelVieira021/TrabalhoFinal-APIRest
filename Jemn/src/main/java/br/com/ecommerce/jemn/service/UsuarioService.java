package br.com.ecommerce.jemn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ecommerce.jemn.dto.usuario.UsuarioRequestDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioResponseDTO;
import br.com.ecommerce.jemn.model.Usuario;
import br.com.ecommerce.jemn.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<UsuarioResponseDTO> obterTodos(){	
		
		List<Usuario> usuarios = usuarioRepository.findAll();

		return usuarios
			.stream()
			.map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public UsuarioResponseDTO obterPorId(Long id) {
		
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		
		return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
	}
	
	public UsuarioResponseDTO adicionar(UsuarioRequestDTO usuarioRequest){
		
		Usuario usuarioModel = mapper.map(usuarioRequest, Usuario.class);
		
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioRequest){
		
		obterPorId(id);
		
		Usuario usuarioModel = mapper.map(usuarioRequest, Usuario.class);
		usuarioModel.setId(id);
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public void deletar(Long id) {
		
		obterPorId(id);
		
		usuarioRepository.deleteById(id);
	}
}
