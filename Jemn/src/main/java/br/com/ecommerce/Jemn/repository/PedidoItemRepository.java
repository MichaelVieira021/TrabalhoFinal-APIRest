package br.com.ecommerce.jemn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.jemn.model.PedidoItem;

public interface PedidoItemRepository extends JpaRepository <PedidoItem, Long> {
    

}
