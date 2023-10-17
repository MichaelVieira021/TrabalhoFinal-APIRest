package br.com.ecommerce.jemn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.jemn.dto.categoria.CategoriaResponseDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoRequestDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoResponseDTO;
import br.com.ecommerce.jemn.model.Categoria;
import br.com.ecommerce.jemn.model.Produto;
import br.com.ecommerce.jemn.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<ProdutoResponseDTO> obterTodos(){
		List<Produto> produtos = produtoRepository.findAll();
		
		return produtos
				.stream()
				.map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
				.collect(Collectors.toList());
	}

	public ProdutoResponseDTO obterPorId(Long id) {
		Optional<Produto> optProduto = produtoRepository.findById(id);
		
		return mapper.map(optProduto.get(), ProdutoResponseDTO.class);
	}

	public ProdutoResponseDTO adicionar(ProdutoRequestDTO produtoRequest){
		
		Long i = produtoRequest.getCategoria().getId();
		CategoriaResponseDTO categoriaResponse = categoriaService.obterPorId(i);		
		produtoRequest.setCategoria(mapper.map(categoriaResponse, Categoria.class));
		Produto produtoModel = mapper.map(produtoRequest, Produto.class);

		produtoModel = produtoRepository.save(produtoModel);
		
		return mapper.map(produtoModel, ProdutoResponseDTO.class);
	}

	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequest){
		obterPorId(id);
		Produto produtoModel = mapper.map(produtoRequest, Produto.class);
		produtoModel.setId(id);
		produtoModel = produtoRepository.save(produtoModel);
		
		return mapper.map(produtoModel, ProdutoResponseDTO.class);
	}

	public void deletar(Long id) {
		obterPorId(id);
		produtoRepository.deleteById(id);
	}
}