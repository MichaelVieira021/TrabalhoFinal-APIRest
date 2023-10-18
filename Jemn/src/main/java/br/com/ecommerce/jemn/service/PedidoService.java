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
import br.com.ecommerce.jemn.dto.produto.ProdutoResponseDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioResponseDTO;
import br.com.ecommerce.jemn.model.FormaPagamento;
import br.com.ecommerce.jemn.model.Pedido;
import br.com.ecommerce.jemn.model.PedidoItem;
import br.com.ecommerce.jemn.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProdutoService produtoService;
	
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
	        
		PedidoResponseDTO teste = mapper.map(pedidoRequest, PedidoResponseDTO.class);
		teste.setVltotalPedido(0);
		teste.setUsuario(mapper.map(usuarioService.obterPorId(teste.getUsuario().getId()), UsuarioResponseDTO.class));
		Pedido pedidoModel = mapper.map(teste, Pedido.class);
		
		pedidoModel = pedidoRepository.save(pedidoModel);
		
		List<PedidoItem> pedidoItens = adicionarPedidoItens(pedidoRequest.getPedidoItens(), pedidoModel);
		pedidoModel.setPedidoItens(pedidoItens);
		
		formaPagamento(pedidoModel);

		pedidoModel = pedidoRepository.save(pedidoModel);	
		
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
        	pdoItemRequest.setPedido(mapper.map(pedidoModel, PedidoResponseDTO.class));
        	
        	ProdutoResponseDTO prResponse = produtoService.obterPorId(pdoItemRequest.getProduto().getId());	
        	pdoItemRequest.setProduto(prResponse);
        	double vlTotal = pdoItemRequest.getProduto().getVlProduto() * pdoItemRequest.getQtdPedidoitem();
        	PedidoItemResponseDTO pedidoItemResponse = mapper.map(pdoItemRequest, PedidoItemResponseDTO.class);
        	pedidoItemResponse.setVltotalItem(vlTotal);
        	pedidoItemResponse =  pedidoItemService.adicionar(mapper.map(pedidoItemResponse, PedidoItemRequestDTO.class));
        	pedidoModel.setVltotalPedido(pedidoModel.getVltotalPedido()+vlTotal);
        	PedidoItem pedidoItem = mapper.map(pedidoItemResponse, PedidoItem.class);
        	prAdicionados.add(pedidoItem);
        }

        return prAdicionados;
    }
    
    public Pedido formaPagamento(Pedido pd) {
    	
    	if(pd.getFormaPg().equals(FormaPagamento.BOLETO) || pd.getFormaPg().equals(FormaPagamento.PIX)) {
    		pd.setDescontoPedido(0.05);
    		pd.setAcrescimoPedido(0);
    		pd.setVltotalPedido(pd.getVltotalPedido()-(pd.getVltotalPedido()*pd.getDescontoPedido()));
    	}else if(pd.getFormaPg().equals(FormaPagamento.CARTAOCREDITO) || pd.getFormaPg().equals(FormaPagamento.CARTAODEBITO)){
    		pd.setAcrescimoPedido(0.05);
    		pd.setDescontoPedido(0);
    		pd.setVltotalPedido(pd.getVltotalPedido()+(pd.getVltotalPedido()*pd.getAcrescimoPedido()));
    	}
	
    	return pd;
    }
}
