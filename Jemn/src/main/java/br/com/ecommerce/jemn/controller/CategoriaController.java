package br.com.ecommerce.jemn.controller;

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
import org.springframework.web.bind.annotation.RestController;
import br.com.ecommerce.jemn.dto.categoria.CategoriaRequestDTO;
import br.com.ecommerce.jemn.dto.categoria.CategoriaResponseDTO;
import br.com.ecommerce.jemn.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ecommerce/categorias")
@Tag(name = "Categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Operation(summary = "Buscar  Todas Categorias Cadastradas", method = "GET",description = "Serve para buscar todas as categorias que ja foram cadastradas, todos podem fazer essa busca")
		@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados ")	
	@GetMapping
	public ResponseEntity<List<CategoriaResponseDTO>> obterTodos(){
		return ResponseEntity.ok(categoriaService.obterTodos());
	}


	@Operation(summary = "Buscar  Categorias Cadastradas  por ID ", method = "GET", description = "Serve para buscar somente por ID as categorias que ja estão cadastradas no BD, todos podem fazer essa busca")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(categoriaService.obterPorId(id));
	}
	
	@Operation(summary = "Buscar  Todas Categorias Cadastradas (APENAS PARA ADMIN) ", method = "GET", description = "Metodo criado somente para o ADMIN caso tenha uma regra de negocio diferente para a pesquisa")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	//APENAS ADMIN---------------------------------------------------------
	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<CategoriaResponseDTO>> obterTodosADMIN(){
		return ResponseEntity.ok(categoriaService.obterTodosADMIN());
	}
	@Operation(summary = "Buscar  por Id Categorias Cadastradas (APENAS PARA ADMIN)  ", method = "GET", description = "Metodo criado somente para o ADMIN caso tenha uma regra de negocio diferente para a pesquisa")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/admin/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CategoriaResponseDTO> obterPorIdADMIN(@PathVariable Long id){
		return ResponseEntity.ok(categoriaService.obterPorIdADMIN(id));
	}
	@Operation(summary = "Adiciona uma nova categoria  ", method = "POST", description = "Metodo com permissão somente para usuario 'ADMIN' ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CategoriaResponseDTO> adicionar(@RequestBody CategoriaRequestDTO categoriaRequest){
		return ResponseEntity.status(201).body(categoriaService.adicionar(categoriaRequest));
	}
	@Operation(summary = "Altera uma Categoria Cadastrada ", method = "PUT", description = "Metodo com permissão somente para usuario 'ADMIN'")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(categoriaService.atualizar(id, categoriaRequest));
	}
	@Operation(summary = "Ativa uma Categoria Cadastrada ", method = "PUT", description = "Metodo com permissão somente para usuario 'ADMIN'")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/ativar/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CategoriaResponseDTO> ativar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(categoriaService.ativar(id));
	}
	@Operation(summary = "Desativa uma Categoria Cadastrada ", method = "PUT", description = "Metodo com permissão somente para usuario 'ADMIN'")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/desativar/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CategoriaResponseDTO> desativar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(categoriaService.desativar(id));
	}
	@Operation(summary = "Deleta uma Categoria Cadastrada ", method = "DELETE", description = "Metodo com permissão somente para usuario 'ADMIN'")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		categoriaService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
