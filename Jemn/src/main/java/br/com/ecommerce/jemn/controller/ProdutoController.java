package br.com.ecommerce.jemn.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.ecommerce.jemn.dto.produto.ProdutoRequestDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoResponseDTO;
import br.com.ecommerce.jemn.model.exceptions.ResourceUnprocessableEntity;
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
	
	@Operation(summary = "Busca todos Produtos Cadastrados ", method = "GET", description = "Metodo com permissão para todos")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodos(){
		return ResponseEntity.ok(produtoService.obterTodos()); 
	}
	
	@Operation(summary = "Busca por id os Produtos Cadastrados ", method = "GET", description = "Metodo com permissão para todos")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorId(id));
	}
	
	@Operation(summary = "Busca todos Produtos Cadastrados pela categoria ", method = "GET", description = "Metodo com permissão para todos")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<ProdutoResponseDTO>> obterPorCategoria(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorCategoria(id));
	}
	
	
	//APENAS ADMIN---------------------------------------------------------
	@Operation(summary = "Busca todos Produtos Cadastrados (APENAS PARA ADMIN) ", method = "GET", description = "Metodo criado somente para o ADMIN caso tenha uma regra de negocio diferente para a pesquisa")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/admin")
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodosADMIN(){
		return ResponseEntity.ok(produtoService.obterTodosADMIN()); 
	}
	
	@Operation(summary = "Busca por id os Produtos Cadastrados (APENAS PARA ADMIN) ", method = "GET", description = "Metodo criado somente para o ADMIN caso tenha uma regra de negocio diferente para a pesquisa")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/admin/{id}")
	public ResponseEntity<ProdutoResponseDTO> obterPorIdADMIN(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorIdADMIN(id));
	}
	
	@Operation(summary = "Busca por categoria os Produtos Cadastrados (APENAS PARA ADMIN) ", method = "GET", description = "Metodo criado somente para o ADMIN caso tenha uma regra de negocio diferente para a pesquisa")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/categoria/admin/{id}")
	public ResponseEntity<List<ProdutoResponseDTO>> obterPorCategoriaADMIN(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorCategoriaADMIN(id));
	}
	
	@Operation(summary = "Cadastra novo produto ", method = "POST", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProdutoResponseDTO> adicionar(@RequestBody ProdutoRequestDTO produtoRequest){
		produtoService.unique(produtoRequest, 0L);
		if(produtoRequest.getNomeProduto().length() > 25){
			throw new  ResourceUnprocessableEntity("Limite de caracteres excedido, MAX:25");
		}
		
		return ResponseEntity.status(201).body(produtoService.adicionar(produtoRequest));
	}
	
	@Operation(summary = "Atualiza produto cadastrado ", method = "PUT", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequest){
		produtoService.unique(produtoRequest, id);
		if(produtoRequest.getNomeProduto().length() > 25){
			throw new  ResourceUnprocessableEntity("Limite de caracteres excedido, MAX:25");
		}
		
		return ResponseEntity.status(200).body(produtoService.atualizar(id, produtoRequest));
	}
	
	@Operation(summary = "Deleta produto cadastrado ", method = "DELETE", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		produtoService.deletar(id);
		return ResponseEntity.status(204).build();
	}
	
	@Operation(summary = "Ativa produto cadastrado ", method = "PUT", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/ativar/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProdutoResponseDTO> ativar(@PathVariable Long id, @RequestBody ProdutoRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(produtoService.ativar(id));
	}
	
	@Operation(summary = "Desativa produto cadastrado ", method = "PUT", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/desativar/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProdutoResponseDTO> desativar(@PathVariable Long id, @RequestBody ProdutoRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(produtoService.desativar(id));
	}
	
	@Operation(summary = "Faz upload de uma foto para o produto cadastrado ", method = "PUT", description = "Metodo criado somente para o ADMIN, adciona uma foto ao produto cadastrado e manda para o banco em base64. ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/upload/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> uploadFotoProduto(@RequestParam("imagem") MultipartFile file,@PathVariable Long id) throws IOException{
		produtoService.enviarImagem(file, id);
		return ResponseEntity.status(200).body("Imagem Adicionada com sucesso!");
		
	}
}
