package br.com.ecommerce.jemn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.ecommerce.jemn.dto.categoria.CategoriaRequestDTO;
import br.com.ecommerce.jemn.dto.categoria.CategoriaResponseDTO;
import br.com.ecommerce.jemn.model.Categoria;
import br.com.ecommerce.jemn.model.ETipoEntidade;
import br.com.ecommerce.jemn.model.Log;
import br.com.ecommerce.jemn.model.Usuario;
import br.com.ecommerce.jemn.model.exceptions.ResourceConflict;
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
		
		List<Categoria> categorias = categoriaRepository.findAll(Sort.by(Sort.Order.asc("id")));

		return categorias
			.stream()
			.filter(categoria -> categoria.isAtivo())
			.map(categoria -> mapper.map(categoria, CategoriaResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public CategoriaResponseDTO obterPorId(Long id){
		Optional<Categoria> optCategoria = categoriaRepository.findById(id);
		
		if(optCategoria.isEmpty() || mapper.map(optCategoria, Categoria.class).isAtivo() == false){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optCategoria.get(), CategoriaResponseDTO.class);
	}
	
	
	//APENAS ADMIN---------------------------------------------------------
	public List<CategoriaResponseDTO> obterTodosADMIN(){	
		
		List<Categoria> categorias = categoriaRepository.findAll(Sort.by(Sort.Order.asc("id")));

		return categorias
			.stream()
			.map(categoria -> mapper.map(categoria, CategoriaResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public CategoriaResponseDTO obterPorIdADMIN(Long id){
		Optional<Categoria> optCategoria = categoriaRepository.findById(id);
		
		if(optCategoria.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optCategoria.get(), CategoriaResponseDTO.class);
	}
	
	@Transactional
	public CategoriaResponseDTO adicionar(CategoriaRequestDTO categoriaRequest){
		Categoria categoriaModel = mapper.map(categoriaRequest, Categoria.class);
		categoriaModel = categoriaRepository.save(categoriaModel);

		try{
			Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Log log = new Log(
				ETipoEntidade.CATEGORIA,
				"CADASTRO",
				"",
				new ObjectMapper().writeValueAsString(categoriaModel),
				usuario
				);

				logService.registrarLog(log); 
			
		}catch(Exception e){
	        throw new RuntimeException("Ocorreu um erro ao adicionar a categoria: " + e.getMessage());
		}
		
		return mapper.map(categoriaModel, CategoriaResponseDTO.class);
	}
	
	@Transactional
	public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoriaRequest){
		var categoriaRegistro = obterPorId(id);
		Categoria categoriaModel = mapper.map(categoriaRequest, Categoria.class);
		categoriaModel.setId(id);
		categoriaModel.setAtivo(categoriaRegistro.isAtivo());
		categoriaModel = categoriaRepository.save(categoriaModel);

		try{
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
            Log log = new Log(
	            ETipoEntidade.CATEGORIA,
	            "ATUALIZACAO", 
	            new ObjectMapper().writeValueAsString(categoriaRegistro),
				new ObjectMapper().writeValueAsString(categoriaModel),
	            usuario
	            );
            
            logService.registrarLog(log);

        }catch(Exception e){
        	throw new RuntimeException("Ocorreu um erro ao atualizar a categoria: " + e.getMessage());
        }
		
		return mapper.map(categoriaModel, CategoriaResponseDTO.class);
	}
	
	public CategoriaResponseDTO ativar(Long id){
		var catAtual = obterPorIdADMIN(id);
		catAtual.setAtivo(true);
		
		return mapper.map(categoriaRepository.save(mapper.map(catAtual, Categoria.class)), CategoriaResponseDTO.class);
	}
	
	public CategoriaResponseDTO desativar(Long id){
		var catAtual = obterPorIdADMIN(id);
		catAtual.setAtivo(false);
		
		return mapper.map(categoriaRepository.save(mapper.map(catAtual, Categoria.class)), CategoriaResponseDTO.class);
	}
	
	@Transactional
	public void deletar(Long id){
		var registroDelete = obterPorId(id);
		categoriaRepository.deleteById(id);

		try{
			Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			Log log = new Log(
				ETipoEntidade.CATEGORIA,
				"DELETE",
				"",
				new ObjectMapper().writeValueAsString(registroDelete),
				usuario);

				logService.registrarLog(log);
			
		}catch(Exception e){
			throw new RuntimeException("Ocorreu um erro ao deletar a categoria: " + e.getMessage());
		}
	}

	public void unique(CategoriaRequestDTO categoriaRequestDTO){
		List<CategoriaResponseDTO> listaCategoriaResponse = obterTodosADMIN();

		for (CategoriaResponseDTO categoriaResponseDTO : listaCategoriaResponse) {
			if(categoriaResponseDTO.getNomeCategoria().equals(categoriaRequestDTO.getNomeCategoria())){
				throw new ResourceConflict("O nome da categoria já existe");
			} 
		}

	}
}
