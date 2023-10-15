package br.com.ecommerce.jemn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.jemn.model.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, Long> {
    

}
