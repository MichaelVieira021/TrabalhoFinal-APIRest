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
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<ProdutoResponseDTO>> obterPorCategoria(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorCategoria(id));
	}
	
	
	//APENAS ADMIN---------------------------------------------------------
	@GetMapping("/admin")
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodosADMIN(){
		return ResponseEntity.ok(produtoService.obterTodosADMIN()); 
	}

	@GetMapping("/admin/{id}")
	public ResponseEntity<ProdutoResponseDTO> obterPorIdADMIN(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorIdADMIN(id));
	}
	
	@GetMapping("/categoria/admin/{id}")
	public ResponseEntity<List<ProdutoResponseDTO>> obterPorCategoriaADMIN(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.obterPorCategoriaADMIN(id));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProdutoResponseDTO> adicionar(@RequestBody ProdutoRequestDTO produtoRequest){
		return ResponseEntity.status(201).body(produtoService.adicionar(produtoRequest));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequest){
		return ResponseEntity.status(200).body(produtoService.atualizar(id, produtoRequest));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		produtoService.deletar(id);
		return ResponseEntity.status(204).build();
	}
	
	@PutMapping("/ativar/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProdutoResponseDTO> ativar(@PathVariable Long id, @RequestBody ProdutoRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(produtoService.ativar(id));
	}
	
	@PutMapping("/desativar/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProdutoResponseDTO> desativar(@PathVariable Long id, @RequestBody ProdutoRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(produtoService.desativar(id));
	}

	@PutMapping("/upload/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> uploadFotoProduto(@RequestParam("imagem") MultipartFile file,@PathVariable Long id) throws IOException{
		
		produtoService.enviarImagem(file, id);

		return ResponseEntity.status(200).body("Imagem Adicionada com sucesso!");
		
	}
}
