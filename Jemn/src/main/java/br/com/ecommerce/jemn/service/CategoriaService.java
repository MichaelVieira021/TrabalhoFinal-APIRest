package br.com.ecommerce.jemn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerce.jemn.dto.categoria.CategoriaRequestDTO;
import br.com.ecommerce.jemn.dto.categoria.CategoriaResponseDTO;
import br.com.ecommerce.jemn.model.Categoria;
import br.com.ecommerce.jemn.model.ETipoEntidade;
import br.com.ecommerce.jemn.model.Log;
import br.com.ecommerce.jemn.model.Usuario;
import br.com.ecommerce.jemn.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LogService logService;
	
	public List<CategoriaResponseDTO> obterTodos(){	
		
		List<Categoria> categorias = categoriaRepository.findAll();

		return categorias
				.stream()
				.filter(categoria -> categoria.isAtivo())
				.map(categoria -> mapper.map(categoria, CategoriaResponseDTO.class))
				.collect(Collectors.toList());	
	}
	
	public CategoriaResponseDTO obterPorId(Long id) {
		
		Optional<Categoria> optCategoria = categoriaRepository.findById(id);
		
		return mapper.map(optCategoria.get(), CategoriaResponseDTO.class);
	}
	
	public CategoriaResponseDTO adicionar(CategoriaRequestDTO categoriaRequest){

		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Categoria categoriaModel = mapper.map(categoriaRequest, Categoria.class);
		categoriaModel.setAtivo(true);
		categoriaModel = categoriaRepository.save(categoriaModel);

		try {
			Log log = new Log(
				ETipoEntidade.CATEGORIA,
				"CADASTRO",
				"",
				new ObjectMapper().writeValueAsString(categoriaModel),
				usuario);

				logService.registrarLog(log);
			
		} catch (Exception e) {
			
		}
		
		return mapper.map(categoriaModel, CategoriaResponseDTO.class);
	}
	
	public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoriaRequest){
		
	
		var categoriaRegistro = obterPorId(id);
		
		Categoria categoriaModel = mapper.map(categoriaRequest, Categoria.class);
		categoriaModel.setId(id);
		categoriaModel = categoriaRepository.save(categoriaModel);

		try {
            
            // Pegando o usuario authenticado para auditoria
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
            Log log = new Log(
            ETipoEntidade.CATEGORIA,
            "ATUALIZACAO", 
            new ObjectMapper().writeValueAsString(categoriaRegistro),
			new ObjectMapper().writeValueAsString(categoriaModel),
            usuario);

            logService.registrarLog(log);

        } catch (Exception e) {
            
        }
		
		return mapper.map(categoriaModel, CategoriaResponseDTO.class);
	}
	
	public void deletar(Long id) {
		
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		var registroDelete = obterPorId(id);
		
		categoriaRepository.deleteById(id);

		try {
			Log log = new Log(
				ETipoEntidade.CATEGORIA,
				"DELETE",
				"",
				new ObjectMapper().writeValueAsString(registroDelete),
				usuario);

				logService.registrarLog(log);
			
		} catch (Exception e) {
			
		}
	}
}
