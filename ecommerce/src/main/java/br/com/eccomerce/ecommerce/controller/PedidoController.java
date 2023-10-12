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

import br.com.eccomerce.ecommerce.model.Pedido;
import br.com.eccomerce.ecommerce.service.PedidoService;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> obterTodos(){
        return ResponseEntity.ok(pedidoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.obterPorId(id));
    }

    public ResponseEntity<Pedido> adicionar(@RequestBody Pedido pedido){
        Pedido pedidoAdicionado = pedidoService.adicionar(pedido);

        return ResponseEntity
            .status(201)
            .body(pedidoAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido){
        Pedido pedidoAtualizado = pedidoService.atualizar(id, pedido);

        return ResponseEntity
            .status(200)
            .body(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id){
        pedidoService.deletar(id);

        return ResponseEntity
            .status(204)
            .build();
    }
}
