package br.com.ecommerce.jemn.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Operation(summary = "Pesquisa e traz todos os pedidoItem Cadastrados ", method = "GET", description = "Metodo criado somente para o ADMIN ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<PedidoItemResponseDTO>> obterTodos(){
		return ResponseEntity.ok(pedidoItemService.obterTodos()); 
	}
	@Operation(summary = "Pesquisa por id um  pedidoItem Cadastrado ", method = "GET", description = "Metodo criado somente para o ADMIN ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PedidoItemResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(pedidoItemService.obterPorId(id));
	}
	@Operation(summary = "Atualiza um pedidoItem cadastrado ", method = "PUT", description = "Metodo criado somente para o ADMIN ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PedidoItemResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoItemRequestDTO pedidoItemRequest){
		return ResponseEntity.status(200).body(pedidoItemService.atualizar(id, pedidoItemRequest));
	}
	@Operation(summary = "Deleta um pedidoItem cadastrado ", method = "PUT", description = "Metodo criado somente para o ADMIN ")
		@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
		@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
		@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
		@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		pedidoItemService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
