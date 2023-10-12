package br.com.eccomerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eccomerce.ecommerce.model.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, Long>{
    
}
