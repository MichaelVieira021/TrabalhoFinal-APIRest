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
	@Operation(summary = "Buscar todos os  Pedidos Cadastrados ", method = "GET",description = "Busca todos os Pedidos Cadastrados, somente permissao 'admin' pode fazer essa busca")

	
	@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados")
	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> obterTodos(){
		return ResponseEntity.ok(pedidoService.obterTodos());
	}
	@Operation(summary = "Buscar  Pedidos Cadastrados  por ID ", method = "GET",description = "Busca um Pedido Cadastrado pelo ID , somente permissao 'admin' pode fazer essa busca")

	
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(pedidoService.obterPorId(id));
	}
	@Operation(summary = "Cadastra  Pedido  ", method = "POST",description = "Metodo para Cadastrar um Pedido, todos tem  a permissão ")

	
	@ApiResponse(responseCode = "200", description = "Pedido Cadastrado com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao Cadastrar o Pedido")
	@PostMapping
	public ResponseEntity<PedidoResponseDTO> adicionar(@RequestBody PedidoRequestDTO pedidoRequest){
		return ResponseEntity.status(201).body(pedidoService.adicionar(pedidoRequest));
	}
	@Operation(summary = "Atualiza Pedido Cadastrado  por ID ", method = "PUT",description = "Metodo para Atualizar um Pedido, todos tem  a permissão")

	
	@ApiResponse(responseCode = "200", description = "Pedido Atualizado com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao atualizar o Pedido")
	@PutMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoRequestDTO pedidoRequest){
		return ResponseEntity.status(200).body(pedidoService.atualizar(id, pedidoRequest));
	}
	@Operation(summary = "Deleta  Pedido Cadastrado  por ID ", method = "DELETE",description = "Metodo para Deletar um Pedido, todos tem  a permissão")

	
		@ApiResponse(responseCode = "200", description = "Pedido Deletado com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao deletar o Pedido por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		pedidoService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
