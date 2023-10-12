package br.com.eccomerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eccomerce.ecommerce.model.PedidoItem;

public interface PedidoItemRepository extends JpaRepository <PedidoItem, Long>{
    
}
