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

import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.ecommerce.jemn.service.PedidoItemService;

@RestController
@RequestMapping("/ecommerce/pedidoItems")
public class PedidoItemController {

	@Autowired
	private PedidoItemService pedidoItemService;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<PedidoItemResponseDTO>> obterTodos(){
		return ResponseEntity.ok(pedidoItemService.obterTodos());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PedidoItemResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(pedidoItemService.obterPorId(id));
	}

	@PostMapping
	public ResponseEntity<PedidoItemResponseDTO> adicionar(@RequestBody PedidoItemRequestDTO pedidoItemRequest){
		return ResponseEntity.status(201).body(pedidoItemService.adicionar(pedidoItemRequest));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PedidoItemResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoItemRequestDTO pedidoItemRequest){
		return ResponseEntity.status(200).body(pedidoItemService.atualizar(id, pedidoItemRequest));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		pedidoItemService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
