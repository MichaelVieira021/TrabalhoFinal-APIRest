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

import br.com.eccomerce.ecommerce.model.PedidoItem;
import br.com.eccomerce.ecommerce.service.PedidoItemService;


@RestController
@RequestMapping("/api/pedido-itens")
public class PedidoItemController {
    

    @Autowired
    private PedidoItemService pedidoItemService;

    @GetMapping
    public ResponseEntity<List<PedidoItem>> obterTodos(){
        return ResponseEntity.ok(pedidoItemService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoItem> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(pedidoItemService.obterPorId(id));
    }

    public ResponseEntity<PedidoItem> adicionar(@RequestBody PedidoItem pedidoItem){
        PedidoItem pedidoItemAdicionado = pedidoItemService.adicionar(pedidoItem);

        return ResponseEntity
            .status(201)
            .body(pedidoItemAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoItem> atualizar(@PathVariable Long id, @RequestBody PedidoItem pedidoItem){
        PedidoItem pedidoItemAtualizado = pedidoItemService.atualizar(id, pedidoItem);

        return ResponseEntity
            .status(200)
            .body(pedidoItemAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id){
        pedidoItemService.deletar(id);

        return ResponseEntity
            .status(204)
            .build();
    }
}
