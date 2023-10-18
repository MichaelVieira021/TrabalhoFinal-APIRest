package br.com.ecommerce.jemn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ecommerce.jemn.dto.categoria.CategoriaRequestDTO;
import br.com.ecommerce.jemn.dto.categoria.CategoriaResponseDTO;
import br.com.ecommerce.jemn.model.Categoria;
import br.com.ecommerce.jemn.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ModelMapper mapper;
	
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
		
		Categoria categoriaModel = mapper.map(categoriaRequest, Categoria.class);
		categoriaModel.setAtivo(true);
		categoriaModel = categoriaRepository.save(categoriaModel);
		
		return mapper.map(categoriaModel, CategoriaResponseDTO.class);
	}
	
	public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoriaRequest){
		
		obterPorId(id);
		
		Categoria categoriaModel = mapper.map(categoriaRequest, Categoria.class);
		categoriaModel.setId(id);
		categoriaModel = categoriaRepository.save(categoriaModel);
		
		return mapper.map(categoriaModel, CategoriaResponseDTO.class);
	}
	
	public void deletar(Long id) {
		
		obterPorId(id);
		
		categoriaRepository.deleteById(id);
	}
}
