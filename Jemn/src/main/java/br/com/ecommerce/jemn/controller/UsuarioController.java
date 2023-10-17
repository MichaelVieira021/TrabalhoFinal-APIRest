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
import br.com.ecommerce.jemn.dto.usuario.UsuarioRequestDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioResponseDTO;
import br.com.ecommerce.jemn.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ecommerce/usuarios")
@Tag(name = "Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Operation(summary = "Buscar  Todas Categorias Cadastradas", method = "GET",description = "Serve para buscar todas as categorias que ja foram cadastradas, somente permissão 'admin' pode fazer essa busca")

	
		@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados ")
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> obterTodos(){
		return ResponseEntity.ok(usuarioService.obterTodos());
	}
	@Operation(summary = "Buscar  Usuarios Cadastrados  por ID ", method = "GET",description = "Busca um Usuario Cadastrado pelo ID , somente permissão 'admin' pode fazer essa busca")

	
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.obterPorId(id));
	}
	@Operation(summary = "Cadastra  Usuario  ", method = "POST",description = "Metodo para Cadastrar um Usuario, todos tem permissão")

	
		@ApiResponse(responseCode = "200", description = "Usuario Cadastrado com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao Cadastrar o Usuario")
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuarioRequest){
		return ResponseEntity.status(201).body(usuarioService.adicionar(usuarioRequest));
	}
	@Operation(summary = "Atualiza Usuario Cadastrado  por ID ", method = "PUT",description = "Metodo para Atualizar um Usuario, somente o usuario conectado pode se alterar")

	
		@ApiResponse(responseCode = "200", description = "Usuario Atualizado com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao atualizar o Usuario")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequest){
		return ResponseEntity.status(200).body(usuarioService.atualizar(id, usuarioRequest));
	}
	@Operation(summary = "Deleta  Usuario Cadastrado  por ID ", method = "DELETE",description = "Metodo para Deletar um Usuario, apenas o usuario conectado pode se deletar")

	
		@ApiResponse(responseCode = "200", description = "Usuario Deletado com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao deletar o Usuario por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		usuarioService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
