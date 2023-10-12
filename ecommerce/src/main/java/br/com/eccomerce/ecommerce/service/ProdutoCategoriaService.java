package br.com.eccomerce.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eccomerce.ecommerce.model.ProdutoCategoria;
import br.com.eccomerce.ecommerce.repository.ProdutoCategoriaRepository;

@Service
public class ProdutoCategoriaService {
    

    @Autowired
    private ProdutoCategoriaRepository produtoCategoriaRepository;

    public List<ProdutoCategoria> obterTodos(){
        return produtoCategoriaRepository.findAll();
    }

    public ProdutoCategoria obterPorId (Long id){
        Optional<ProdutoCategoria> optProdutoCategoria = produtoCategoriaRepository.findById(id);

        if (optProdutoCategoria.isEmpty()) {
            throw new RuntimeException("Nenhum produtoCategoria encontrado para o ID: " + id);
            
        }

        return optProdutoCategoria.get();
    }

    public ProdutoCategoria adicionar(ProdutoCategoria produtoCategoria){
        produtoCategoria.setId(null);
        return produtoCategoriaRepository.save(produtoCategoria);
    }


    public ProdutoCategoria atualizar(long id, ProdutoCategoria produtoCategoria){

        obterPorId(id);

        produtoCategoria.setId(id);
        return produtoCategoriaRepository.save(produtoCategoria);
    }

    public void deletar(long id){
        obterPorId(id);

        produtoCategoriaRepository.deleteById(id);
    }
}
