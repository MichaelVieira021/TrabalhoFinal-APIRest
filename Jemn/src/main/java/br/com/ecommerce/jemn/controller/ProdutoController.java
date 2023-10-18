package br.com.ecommerce.jemn.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import br.com.ecommerce.jemn.dto.produto.ProdutoRequestDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoResponseDTO;

import br.com.ecommerce.jemn.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/ecommerce/produtos")
@Tag(name = "Produto")
public class ProdutoController {

	

	@Autowired
	private ProdutoService produtoService;

	
	@Operation(summary = "Buscar todos os  Produtos Cadastrados ", method = "GET",description = "Busca todos os Produtos Cadastrados, todos podem fazer essa busca")

	
		@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados")
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodos(){
		return ResponseEntity.ok(produtoService.obterTodos());
	}
@Operation(summary = "Buscar  Produtos Cadastrados  por ID ", method = "GET",description = "Busca um produto Cadastrado pelo ID , Todos Podem fazer essa busca")

	
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorId(id));
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<ProdutoResponseDTO>> obterPorCategoria(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorCategoria(id));
	}

	
		@ApiResponse(responseCode = "200", description = "Produto Cadastrado com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao Cadastrar o Produto")
	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> adicionar(@RequestBody ProdutoRequestDTO produtoRequest){
		
		return ResponseEntity.status(201).body(produtoService.adicionar(produtoRequest));
	}
@Operation(summary = "Atualiza Produto Cadastrado  por ID ", method = "PUT",description = "Metodo para Atualizar um Produto, sendo possivel Atualizar somente tendo a permissão de 'admin'")

	
		@ApiResponse(responseCode = "200", description = "Produto Atualizado com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao atualizar o Produto")
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequest){
		return ResponseEntity.status(200).body(produtoService.atualizar(id, produtoRequest));
	}
  @Operation(summary = "Deleta  Produto Cadastrado  por ID ", method = "DELETE",description = "Metodo para Deletar um Produto, sendo possivel deletar somente tendo a permissão de 'admin'")

	
		@ApiResponse(responseCode = "200", description = "Produto Deletado com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao deletar o Produto por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		produtoService.deletar(id);
		return ResponseEntity.status(204).build();
	}

}
