package br.com.ecommerce.jemn.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.ecommerce.jemn.dto.categoria.CategoriaResponseDTO;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoRequestDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoResponseDTO;
import br.com.ecommerce.jemn.model.ETipoEntidade;
import br.com.ecommerce.jemn.model.Log;
import br.com.ecommerce.jemn.model.Produto;
import br.com.ecommerce.jemn.model.Usuario;
import br.com.ecommerce.jemn.repository.ProdutoRepository;

@Service
public class ProdutoService { 

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private LogService logService;
	
	public List<ProdutoResponseDTO> obterTodos(){
		List<Produto> produtos = produtoRepository.findAll();
		
		return produtos
			.stream()
			.filter(produto -> produto.isAtivo())
			.filter(cat -> cat.getCategoria().isAtivo())
			.map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
			.collect(Collectors.toList());
	}

	public ProdutoResponseDTO obterPorId(Long id) {
		Optional<Produto> optProduto = produtoRepository.findById(id);
		
		if(optProduto.isEmpty() || mapper.map(optProduto, Produto.class).isAtivo() == false || mapper.map(optProduto, Produto.class).getCategoria().isAtivo() == false){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optProduto.get(), ProdutoResponseDTO.class);
	}
	
	public List<ProdutoResponseDTO> obterPorCategoria(Long id) {
		List<ProdutoResponseDTO> produtos = obterTodos();
		List<Produto> prs = new ArrayList<>();
		
		for(ProdutoResponseDTO pr : produtos) {
			if(pr.getCategoria().getId() == id){
				prs.add(mapper.map(pr, Produto.class));
			}
		}
		return prs
				.stream()
				.filter((categoria -> categoria.isAtivo()))
				.map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
				.collect(Collectors.toList());
	}
	
	
	//APENAS ADMIN---------------------------------------------------------
	public List<ProdutoResponseDTO> obterTodosADMIN(){
		List<Produto> produtos = produtoRepository.findAll();
		
		return produtos
			.stream()
			.map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
			.collect(Collectors.toList());
	}

	public ProdutoResponseDTO obterPorIdADMIN(Long id) {
		Optional<Produto> optProduto = produtoRepository.findById(id);
		
		if(optProduto.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optProduto.get(), ProdutoResponseDTO.class);
	}
	
	public List<ProdutoResponseDTO> obterPorCategoriaADMIN(Long id) {
		List<ProdutoResponseDTO> produtos = obterTodosADMIN();
		List<Produto> prs = new ArrayList<>();
		
		for(ProdutoResponseDTO pr : produtos) {
			if(pr.getCategoria().getId() == id){
				prs.add(mapper.map(pr, Produto.class));
			}
		}
		return prs
			.stream()
			.map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
			.collect(Collectors.toList());
	}

	@Transactional
	public ProdutoResponseDTO adicionar(ProdutoRequestDTO produtoRequest){
		CategoriaResponseDTO categoriaResponse = categoriaService.obterPorIdADMIN(produtoRequest.getCategoria().getId());		
		produtoRequest.setCategoria(categoriaResponse);
		Produto produtoModel = mapper.map(produtoRequest, Produto.class);
		produtoModel = produtoRepository.save(produtoModel);

		try {
			Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			Log log = new Log(
				ETipoEntidade.PRODUTO,
				"CADASTRO",
				"",
				new ObjectMapper().writeValueAsString(produtoModel),
				usuario
				);

				logService.registrarLog(log);
			
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao adicionar o produto: " + e.getMessage());
		}
		
		return mapper.map(produtoModel, ProdutoResponseDTO.class);
	}

	@Transactional
	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequest){
		var produtoRegistro = obterPorId(id);
		Produto produtoModel = mapper.map(produtoRequest, Produto.class);
		produtoModel.setId(id);
		produtoModel.setAtivo(produtoRegistro.isAtivo());
		produtoModel = produtoRepository.save(produtoModel);

		try {
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
            Log log = new Log(
            ETipoEntidade.PRODUTO,
            "ATUALIZACAO", 
            new ObjectMapper().writeValueAsString(produtoRegistro),
			new ObjectMapper().writeValueAsString(produtoModel),
            usuario
            );

            logService.registrarLog(log);

        } catch (Exception e) {
        	throw new RuntimeException("Ocorreu um erro ao atualizar o produto: " + e.getMessage());
        }
		return mapper.map(produtoModel, ProdutoResponseDTO.class);
	}

	@Transactional
    public ProdutoResponseDTO atualizarQtd(ProdutoResponseDTO produtoRequest, PedidoItemResponseDTO pedidoItemResponse){
        var produtoRegistro = obterPorId(produtoRequest.getId());
        Produto produtoModel = mapper.map(produtoRequest, Produto.class);
        produtoModel.setId(produtoRequest.getId());
        produtoModel.setQtdProduto(produtoModel.getQtdProduto() - pedidoItemResponse.getQtdPedidoitem());
        produtoModel.setAtivo(produtoRegistro.isAtivo());
        produtoModel = produtoRepository.save(produtoModel);

        try {
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
            Log log = new Log(
            ETipoEntidade.PRODUTO,
            "ATUALIZACAO", 
            new ObjectMapper().writeValueAsString(produtoRegistro),
            new ObjectMapper().writeValueAsString(produtoModel),
            usuario
            );

            logService.registrarLog(log);

        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao atualizar o produto: " + e.getMessage());
        }
        return mapper.map(produtoModel, ProdutoResponseDTO.class);
    }
	
	@Transactional
	public void deletar(Long id) {
		var registroDelete = obterPorId(id);
		produtoRepository.deleteById(id);

		try {
			Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Log log = new Log(
				ETipoEntidade.PRODUTO,
				"DELETE",
				new ObjectMapper().writeValueAsString(registroDelete),
				"",
				usuario
				);
				
				logService.registrarLog(log);
			
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao deletar o produto: " + e.getMessage());
		}
	}
	
	public ProdutoResponseDTO ativar(Long id) {
		var prAtual = obterPorIdADMIN(id);
		prAtual.setAtivo(true);
		
		return mapper.map(produtoRepository.save(mapper.map(prAtual, Produto.class)), ProdutoResponseDTO.class);
	}
	
	public ProdutoResponseDTO desativar(Long id) {
		var prAtual = obterPorIdADMIN(id);
		prAtual.setAtivo(false);
		
		return mapper.map(produtoRepository.save(mapper.map(prAtual, Produto.class)), ProdutoResponseDTO.class);
	}

	public void enviarImagem(@RequestParam("imagem") MultipartFile file,@PathVariable Long id) throws IOException{
		String base64File = Base64.getEncoder().encodeToString(file.getBytes()); 
		Produto produtoModel = mapper.map(obterPorIdADMIN(id), Produto.class);
		produtoModel.setFlieBase64(base64File);
		produtoRepository.save(produtoModel);	
	}
}
