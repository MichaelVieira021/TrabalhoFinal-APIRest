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

import br.com.eccomerce.ecommerce.model.ProdutoCategoria;
import br.com.eccomerce.ecommerce.service.ProdutoCategoriaService;


@RestController
@RequestMapping("/api/produto-categorias")
public class ProdutoCategoriaController {
    

    @Autowired
    private ProdutoCategoriaService produtoCategoriaService;

    @GetMapping
    public ResponseEntity<List<ProdutoCategoria>> obterTodos(){
        return ResponseEntity.ok(produtoCategoriaService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoCategoria> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(produtoCategoriaService.obterPorId(id));
    }

    public ResponseEntity<ProdutoCategoria> adicionar(@RequestBody ProdutoCategoria produtoCategoria){
        ProdutoCategoria produtoCategoriaAdicionado = produtoCategoriaService.adicionar(produtoCategoria);

        return ResponseEntity
            .status(201)
            .body(produtoCategoriaAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoCategoria> atualizar(@PathVariable Long id, @RequestBody ProdutoCategoria produtoCategoria){
        ProdutoCategoria produtoCategoriaAtualizado = produtoCategoriaService.atualizar(id, produtoCategoria);

        return ResponseEntity
            .status(200)
            .body(produtoCategoriaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id){
        produtoCategoriaService.deletar(id);

        return ResponseEntity
            .status(204)
            .build();
    }
}
