package br.com.ecommerce.jemn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.ecommerce.jemn.model.Pedido;
import br.com.ecommerce.jemn.model.PedidoItem;
import br.com.ecommerce.jemn.repository.PedidoItemRepository;

@Service
public class PedidoItemService {
	
	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<PedidoItemResponseDTO> obterTodos(){	
		List<PedidoItem> pedidoItems = pedidoItemRepository.findAll(); 

		return pedidoItems
			.stream()
			.map(pedidoItem -> mapper.map(pedidoItem, PedidoItemResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public PedidoItemResponseDTO obterPorId(Long id) {
		Optional<PedidoItem> optPedidoItem = pedidoItemRepository.findById(id);
		
		if(optPedidoItem.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optPedidoItem.get(), PedidoItemResponseDTO.class);
	}
	
	public PedidoItemResponseDTO adicionar(PedidoItemRequestDTO pedidoItemRequest){
		return mapper.map(pedidoItemRepository.save(mapper.map(pedidoItemRequest, PedidoItem.class)), PedidoItemResponseDTO.class);
	}
	
	public PedidoItemResponseDTO atualizar(Long id, PedidoItemRequestDTO pedidoItemRequest){
		obterPorId(id);
		PedidoItem pedidoItemModel = mapper.map(pedidoItemRequest, PedidoItem.class);
		pedidoItemModel.setId(id);
		pedidoItemModel = pedidoItemRepository.save(pedidoItemModel);
		
		return mapper.map(pedidoItemModel, PedidoItemResponseDTO.class);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		pedidoItemRepository.deleteById(id);
	}

	public void deletarAllDePedido(Pedido pedido){
		List<PedidoItem> pdIExcluir = pedidoItemRepository.findByPedido(pedido);
		for(PedidoItem pdI : pdIExcluir){
			deletar(pdI.getId());
		}
	}
}
