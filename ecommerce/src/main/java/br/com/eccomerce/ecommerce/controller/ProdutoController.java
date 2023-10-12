package br.com.eccomerce.ecommerce.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eccomerce.ecommerce.model.Produto;
import br.com.eccomerce.ecommerce.service.ProdutoService;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> obterTodos(){
        return ResponseEntity.ok(produtoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.obterPorId(id));
    }

    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto){
        Produto produtoAdicionado = produtoService.adicionar(produto);

        return ResponseEntity
            .status(201)
            .body(produtoAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoService.atualizar(id, produto);

        return ResponseEntity
            .status(200)
            .body(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id){
        produtoService.deletar(id);

        return ResponseEntity
            .status(204)
            .build();
    }
}
