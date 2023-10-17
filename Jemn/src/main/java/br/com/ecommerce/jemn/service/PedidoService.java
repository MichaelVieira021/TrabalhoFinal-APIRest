package br.com.ecommerce.jemn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.ecommerce.jemn.dto.pedido.PedidoRequestDTO;
import br.com.ecommerce.jemn.dto.pedido.PedidoResponseDTO;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.ecommerce.jemn.model.Pedido;
import br.com.ecommerce.jemn.model.PedidoItem;
import br.com.ecommerce.jemn.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	//@Autowired
	//private UsuarioService usuarioService;
	
	@Autowired
	private PedidoItemService pedidoItemService;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<PedidoResponseDTO> obterTodos(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		
		return pedidos
			.stream()
			.map(pedido -> mapper.map(pedido, PedidoResponseDTO.class))
			.collect(Collectors.toList());
	}

	public PedidoResponseDTO obterPorId(Long id) {
		Optional<Pedido> optPedido = pedidoRepository.findById(id);
		
		return mapper.map(optPedido.get(), PedidoResponseDTO.class);
	}

	
	@Transactional
	public PedidoResponseDTO adicionar(PedidoRequestDTO pedidoRequest){
	        
		Pedido pedidoModel = mapper.map(pedidoRequest, Pedido.class);
		
		pedidoModel = pedidoRepository.save(pedidoModel);
		
		List<PedidoItem> pedidoItens = adicionarPedidoItens(pedidoRequest.getPedidoItens(), pedidoModel);
		pedidoModel.setPedidoItens(pedidoItens);	
		
		return mapper.map(pedidoModel, PedidoResponseDTO.class);
	}
	
	

	public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedidoRequest){
		obterPorId(id);
		Pedido pedidoModel = mapper.map(pedidoRequest, Pedido.class);
		pedidoModel.setId(id);
		pedidoModel = pedidoRepository.save(pedidoModel);
		
		return mapper.map(pedidoModel, PedidoResponseDTO.class);
	}

	public void deletar(Long id) {
		obterPorId(id);
		pedidoRepository.deleteById(id);
	}
	
    private List<PedidoItem> adicionarPedidoItens(List<PedidoItemRequestDTO> pedidoItemRequest, Pedido pedidoModel){
        
        List<PedidoItem> prAdicionados = new ArrayList<>();

        for(PedidoItemRequestDTO pdoItemRequest : pedidoItemRequest){
        	pdoItemRequest.setIdPedido(mapper.map(pedidoModel, PedidoRequestDTO.class));
        	
        	double vlTotal = pdoItemRequest.getIdProduto().getVlProduto() * pdoItemRequest.getQtdPedidoitem();
        	PedidoItemResponseDTO pedidoItemResponse =  pedidoItemService.adicionar(pdoItemRequest);
        	pedidoItemResponse.setVltotalItem(vlTotal);
        	PedidoItem pedidoItem = mapper.map(pedidoItemResponse, PedidoItem.class);
        	prAdicionados.add(pedidoItem);
        }

        return prAdicionados;
    }
}
