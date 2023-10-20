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

import br.com.ecommerce.jemn.dto.pedido.PedidoRequestDTO;
import br.com.ecommerce.jemn.dto.pedido.PedidoResponseDTO;
import br.com.ecommerce.jemn.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ecommerce/pedidos")
@Tag(name = "Pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Operation(summary = "Pesquisa e traz todos os pedidos cadastrado ", method = "GET", description = "Metodo criado somente para o ADMIN ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<PedidoResponseDTO>> obterTodos(){
		return ResponseEntity.ok(pedidoService.obterTodos());
	}
	@Operation(summary = "Pesquisa e traz por Id pedidos cadastrado ", method = "GET", description = "Metodo criado somente para o ADMIN ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PedidoResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(pedidoService.obterPorId(id));
	}
	@Operation(summary = "Cria um novo pedido ", method = "POST", description = "Metodo permitido para usuarios com autenticação ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PostMapping
	public ResponseEntity<PedidoResponseDTO> adicionar(@RequestBody PedidoRequestDTO pedidoRequest){
		return ResponseEntity.status(201).body(pedidoService.adicionar(pedidoRequest));
	}
	@Operation(summary = "Atualiza um pedido cadastrado ", method = "PUT", description = "Metodo criado somente para o ADMIN ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoRequestDTO pedidoRequest){
		return ResponseEntity.status(200).body(pedidoService.atualizar(id, pedidoRequest));
	}
	@Operation(summary = "Deleta um pedido cadastrado ", method = "DELETE", description = "Metodo criado somente para o ADMIN ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		pedidoService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}

