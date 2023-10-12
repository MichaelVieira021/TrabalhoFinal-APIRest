package br.com.eccomerce.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eccomerce.ecommerce.model.Produto;
import br.com.eccomerce.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
    

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> obterTodos(){
        return produtoRepository.findAll();
    }

    public Produto obterPorId (Long id){
        Optional<Produto> optProduto = produtoRepository.findById(id);

        if (optProduto.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado para o ID: " + id);
            
        }

        return optProduto.get();
    }

    public Produto adicionar(Produto produto){
        produto.setId(null);
        return produtoRepository.save(produto);
    }


    public Produto atualizar(long id, Produto produto){

        obterPorId(id);

        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public void deletar(long id){
        obterPorId(id);

        produtoRepository.deleteById(id);
    }
}
