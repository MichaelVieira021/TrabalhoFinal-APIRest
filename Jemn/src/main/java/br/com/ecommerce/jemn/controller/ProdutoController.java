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

@RestController
@RequestMapping("/ecommerce/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodos(){
		return ResponseEntity.ok(produtoService.obterTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorId(id));
	}

	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> adicionar(@RequestBody ProdutoRequestDTO produtoRequest){
		return ResponseEntity.status(201).body(produtoService.adicionar(produtoRequest));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequest){
		return ResponseEntity.status(200).body(produtoService.atualizar(id, produtoRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		produtoService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
