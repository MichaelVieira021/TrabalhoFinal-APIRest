package br.com.ecommerce.jemn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.ecommerce.jemn.dto.pedido.PedidoRequestDTO;
import br.com.ecommerce.jemn.dto.pedido.PedidoResponseDTO;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.ecommerce.jemn.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.ecommerce.jemn.dto.produto.ProdutoResponseDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioResponseDTO;
import br.com.ecommerce.jemn.model.FormaPagamento;
import br.com.ecommerce.jemn.model.Pedido;
import br.com.ecommerce.jemn.model.PedidoItem;
import br.com.ecommerce.jemn.model.email.Email;
import br.com.ecommerce.jemn.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private EmailService emailService;

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
		
		testeEnvioDeEmail(pedidoModel);
		
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
        	PedidoItemResponseDTO pedidoItemResponse = mapper.map(pdoItemRequest, PedidoItemResponseDTO.class);
        	
			if (pedidoItemResponse.getQtdPedidoitem() > pedidoItemResponse.getProduto().getQtdProduto()){
 				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade solicitada é maior que a quantidade em estoque");			
			}

			if (pedidoItemResponse.getQtdPedidoitem() >= 5){
				pedidoItemResponse.setDescontoItem((pedidoItemResponse.getProduto().getVlProduto()* pedidoItemResponse.getQtdPedidoitem())* 0.05);
				pedidoModel.setDescontoPedido(pedidoModel.getDescontoPedido() + pedidoItemResponse.getDescontoItem());
			}

			double vlTotalItem = (pedidoItemResponse.getProduto().getVlProduto() - pedidoItemResponse.getDescontoItem() + pedidoItemResponse.getAcrecimoItem()) * pedidoItemResponse.getQtdPedidoitem();
        	pedidoItemResponse.setVltotalItem(vlTotalItem);
        	pedidoItemResponse =  pedidoItemService.adicionar(mapper.map(pedidoItemResponse, PedidoItemRequestDTO.class));
        	pedidoModel.setVltotalPedido(pedidoModel.getVltotalPedido()+vlTotalItem);
        	PedidoItem pedidoItem = mapper.map(pedidoItemResponse, PedidoItem.class);
        	prAdicionados.add(pedidoItem);
        }

        return prAdicionados;

		
    }

	 public ResponseEntity<?> testeEnvioDeEmail(Pedido pedido) {

        //List<String> destinatarios = new ArrayList<>();
        //destinatarios.add("janieltonmedeiros@outlook.com");

		String destinatario = pedido.getUsuario().getEmail();
        // Obtém todos os pedidos
        //List<PedidoResponseDTO> pedidos = pedidoService.obterTodos();

        // Gera o HTML com os dados dos pedidos
        String mensagem = gerarHTMLComDadosDosPedidos(pedido);

        // Cria o email
        Email email = new Email("RESUMO PEDIDO JEMN", mensagem, "elton.medeiros14@gmail.com", destinatario);

        // Envia o email
        emailService.enviar(email);

        return ResponseEntity.status(200).body("E-mail enviado com sucesso!!!");
    }

    private String gerarHTMLComDadosDosPedidos(Pedido pedido) {
        StringBuilder html = new StringBuilder();

        // Cabeçalho do HTML
        // html.append("<h1>Resumo de pedidos</h1>");

        // Tabela com os dados dos pedidos
        
            html.append("<style>");
            html.append("table {");
            html.append("border-collapse: collapse;");
            html.append("width: 100%;");
            html.append("}");

            html.append("th, td {");
            html.append("padding: 8px;");
            html.append("border: 1px solid black;");
            html.append("}");

            html.append("th {");
            html.append("background-color: #ffcbdb;");
            html.append("text-align: center;");
            html.append("}");

            html.append("td {");
            html.append("text-align: left;");
            html.append("}");
            html.append("</style>");

            html.append("<div style=\"text-align: center;\">");
            html.append("<h1>Seu Pedido Jemn ❤️</h1>");
            html.append("<table style=\"border-collapse: collapse;\">");
            html.append("<tr>");
            html.append("<th>Número do pedido</th>");
			html.append("<th>Valor total do pedido</th>");
            html.append("</tr>");

        
            html.append("<tr>");
            html.append("<td>" + pedido.getId() + "</td>");
			html.append("<td>" + pedido.getVltotalPedido() + "</td>");
            html.append("</tr>");
       		html.append("</table>");

        // Rodape do HTML
        html.append("<p><h3>Obrigado por seu pedido! 🥰</h3></p>");

        return html.toString();
    }
    
    public Pedido formaPagamento(Pedido pd) {
    	
    	if(pd.getFormaPg().equals(FormaPagamento.BOLETO) || pd.getFormaPg().equals(FormaPagamento.PIX)) {
    		pd.setDescontoPedido(pd.getDescontoPedido() + (pd.getVltotalPedido() * 0.05));
    		pd.setVltotalPedido(pd.getVltotalPedido() - (pd.getVltotalPedido() * 0.05));
    	}else if(pd.getFormaPg().equals(FormaPagamento.CARTAOCREDITO) || pd.getFormaPg().equals(FormaPagamento.CARTAODEBITO)){
    		pd.setAcrescimoPedido(pd.getAcrescimoPedido() + (pd.getVltotalPedido() * 0.05));
    		pd.setVltotalPedido(pd.getVltotalPedido() + (pd.getVltotalPedido()* 0.05));
    	}
	
    	return pd;
    }
}
