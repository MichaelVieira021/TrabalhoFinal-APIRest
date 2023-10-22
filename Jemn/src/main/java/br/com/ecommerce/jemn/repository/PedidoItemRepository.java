package br.com.ecommerce.jemn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.jemn.model.Pedido;
import br.com.ecommerce.jemn.model.PedidoItem;
import java.util.List;


public interface PedidoItemRepository extends JpaRepository <PedidoItem, Long> {
    List<PedidoItem> findByPedido(Pedido pedido);
}
