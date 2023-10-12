package br.com.eccomerce.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eccomerce.ecommerce.model.Pedido;
import br.com.eccomerce.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
    

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> obterTodos(){
        return pedidoRepository.findAll();
    }

    public Pedido obterPorId (Long id){
        Optional<Pedido> optPedido = pedidoRepository.findById(id);

        if (optPedido.isEmpty()) {
            throw new RuntimeException("Nenhum pedido encontrado para o ID: " + id);
            
        }

        return optPedido.get();
    }

    public Pedido adicionar(Pedido pedido){
        pedido.setId(null);
        return pedidoRepository.save(pedido);
    }


    public Pedido atualizar(long id, Pedido pedido){

        obterPorId(id);

        pedido.setId(id);
        return pedidoRepository.save(pedido);
    }

    public void deletar(long id){
        obterPorId(id);

        pedidoRepository.deleteById(id);
    }
}
