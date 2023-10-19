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

@RestController
@RequestMapping("/ecommerce/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaResponseDTO>> obterTodos(){
		return ResponseEntity.ok(categoriaService.obterTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(categoriaService.obterPorId(id));
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CategoriaResponseDTO> adicionar(@RequestBody CategoriaRequestDTO categoriaRequest){
		return ResponseEntity.status(201).body(categoriaService.adicionar(categoriaRequest));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequest){
		return ResponseEntity.status(200).body(categoriaService.atualizar(id, categoriaRequest));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		categoriaService.deletar(id);
		return ResponseEntity.status(204).build();
	}
}
