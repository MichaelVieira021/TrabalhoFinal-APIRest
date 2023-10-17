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

import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.ecommerce.jemn.service.PedidoItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ecommerce/pedidoItems")
@Tag(name = "PedidoItem")
public class PedidoItemController {

	@Autowired
	private PedidoItemService pedidoItemService;
	@Operation(summary = "Buscar todos os  PedidoItems Cadastrados ", method = "GET",description = "Busca todos os PedidoItems Cadastrados, todos podem fazer essa busca")

	
		@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados")
	@GetMapping
	public ResponseEntity<List<PedidoItemResponseDTO>> obterTodos(){
		return ResponseEntity.ok(pedidoItemService.obterTodos());
	}
	@Operation(summary = "Buscar  PedidoItems Cadastrados  por ID ", method = "GET",description = "Busca um PedidoItem Cadastrado pelo ID , Todos Podem fazer essa busca")

	
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/{id}")
	public ResponseEntity<PedidoItemResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(pedidoItemService.obterPorId(id));
	}
	@Operation(summary = "Cadastra  PedidoItem  ", method = "POST",description = "Metodo para Cadastrar um PedidoItem, Todos tem permissão")

	
	@ApiResponse(responseCode = "200", description = "PedidoItem Cadastrado com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao Cadastrar o PedidoItem")
	@PostMapping
	public ResponseEntity<PedidoItemResponseDTO> adicionar(@RequestBody PedidoItemRequestDTO pedidoItemRequest){
		return ResponseEntity.status(201).body(pedidoItemService.adicionar(pedidoItemRequest));
	}
	@Operation(summary = "Atualiza PedidoItem Cadastrado  por ID ", method = "PUT",description = "Metodo para Atualizar um PedidoItem, Todos tem permissão")

	
	@ApiResponse(responseCode = "200", description = "PedidoItem Atualizado com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao atualizar o PedidoItem")
	@PutMapping("/{id}")
	public ResponseEntity<PedidoItemResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoItemRequestDTO pedidoItemRequest){
		return ResponseEntity.status(200).body(pedidoItemService.atualizar(id, pedidoItemRequest));
	}
	@Operation(summary = "Deleta  PedidoItem Cadastrado  por ID ", method = "DELETE",description = "Metodo para Deletar um PedidoItem, Todos tem permissão")

	
	@ApiResponse(responseCode = "200", description = "PedidoItem Deletado com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao deletar o PedidoItem por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		pedidoItemService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
