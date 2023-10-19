package br.com.ecommerce.jemn.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
				.filter(produto -> produto.isAtivo())
				.map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
				.collect(Collectors.toList());
	}

	public ProdutoResponseDTO obterPorId(Long id) {
		Optional<Produto> optProduto = produtoRepository.findById(id);
		
		return mapper.map(optProduto.get(), ProdutoResponseDTO.class);
	}
	
	public List<ProdutoResponseDTO> obterPorCategoria(Long id) {
		List<Produto> produtos = produtoRepository.findAll();
		
		List<Produto> prs = new ArrayList<>();
		
		for(Produto pr : produtos) {
			if(pr.getCategoria().getId() == id){
				prs.add(pr);
			}
		}
		return prs
				.stream()
				.filter((categoria -> categoria.isAtivo()))
				.map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
				.collect(Collectors.toList());
	}

	public ProdutoResponseDTO adicionar(ProdutoRequestDTO produtoRequest){
		
		Long i = produtoRequest.getCategoria().getId();
		CategoriaResponseDTO categoriaResponse = categoriaService.obterPorId(i);		
		produtoRequest.setCategoria(mapper.map(categoriaResponse, Categoria.class));
		Produto produtoModel = mapper.map(produtoRequest, Produto.class);
		produtoModel.setAtivo(true);

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

	public void enviarImagem(@RequestParam("file") MultipartFile file,@PathVariable Long id) throws IOException{

		String diretorio ="C:\\Imagens\\";
		
		byte[] fileBytes = file.getBytes();
		String base64File = Base64.getEncoder().encodeToString(fileBytes); 
		Path nomeFile = Paths.get(diretorio,file.getOriginalFilename());
		Files.write(nomeFile,file.getBytes());
		

		ProdutoResponseDTO produtoEncontrado =  obterPorId(id); 		
		Produto produtoModel = mapper.map(produtoEncontrado, Produto.class);
		produtoModel.setFlieBase64(base64File);
		produtoRepository.save(produtoModel);
		
		
		
	}
}
