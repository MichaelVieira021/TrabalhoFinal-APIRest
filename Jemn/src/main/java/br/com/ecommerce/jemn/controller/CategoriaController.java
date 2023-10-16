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
	@Operation(summary = "Cadastra Categorias ", method = "POST", description = "Metodo para cadastrar as Categorias (sendo possivel cadastrar somente tendo a permissão de 'admin')")

	
		@ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao Cadastrar a Categoria")
	@PostMapping
	public ResponseEntity<CategoriaResponseDTO> adicionar(@RequestBody CategoriaRequestDTO categoriaRequest){
		return ResponseEntity.status(201).body(categoriaService.adicionar(categoriaRequest));
	}
	@Operation(summary = "Atualiza Categorias Cadastradas ", method = "PUT", description = "Metodo para atualizar uma Categoria por ID , sendo possivel atualizar somente tendo a permissão de 'admin'")

	
		@ApiResponse(responseCode = "200", description = "Categoria Atualizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao Atualizar Categoria")
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(categoriaService.atualizar(id, categoriaRequest));
	}
	@Operation(summary = "Deleta Categoria por ID ", method = "DELETE",description = "Metodo para Deletar uma categoria, sendo possivel deletar somente tendo a permissão de 'admin'")

	
		@ApiResponse(responseCode = "200", description = "Categoria deletada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao deletar a Categoria")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		categoriaService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
