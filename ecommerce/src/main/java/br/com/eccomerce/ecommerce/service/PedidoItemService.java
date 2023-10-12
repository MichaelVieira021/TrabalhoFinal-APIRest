package br.com.eccomerce.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eccomerce.ecommerce.model.PedidoItem;
import br.com.eccomerce.ecommerce.repository.PedidoItemRepository;

@Service
public class PedidoItemService {
    

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    public List<PedidoItem> obterTodos(){
        return pedidoItemRepository.findAll();
    }

    public PedidoItem obterPorId (Long id){
        Optional<PedidoItem> optPedidoItem = pedidoItemRepository.findById(id);

        if (optPedidoItem.isEmpty()) {
            throw new RuntimeException("Nenhum pedidoItem encontrado para o ID: " + id);
            
        }

        return optPedidoItem.get();
    }

    public PedidoItem adicionar(PedidoItem pedidoItem){
        pedidoItem.setId(0);
        return pedidoItemRepository.save(pedidoItem);
    }


    public PedidoItem atualizar(long id, PedidoItem pedidoItem){

        obterPorId(id);

        pedidoItem.setId(id);
        return pedidoItemRepository.save(pedidoItem);
    }

    public void deletar(long id){
        obterPorId(id);

        pedidoItemRepository.deleteById(id);
    }
}
