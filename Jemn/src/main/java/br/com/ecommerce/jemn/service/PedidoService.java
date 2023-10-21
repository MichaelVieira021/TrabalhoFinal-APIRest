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
		
		if(optPedido.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optPedido.get(), PedidoResponseDTO.class);
	}

	@Transactional
	public PedidoResponseDTO adicionar(PedidoRequestDTO pedidoRequest){
		pedidoRequest.setUsuario(mapper.map(usuarioService.obterPorId(pedidoRequest.getUsuario().getId()), UsuarioResponseDTO.class));
		Pedido pedidoModel = pedidoRepository.save(mapper.map(pedidoRequest, Pedido.class));
		
		List<PedidoItem> pedidoItens = adicionarPedidoItens(pedidoRequest.getPedidoItens(), pedidoModel);
		pedidoModel.setPedidoItens(pedidoItens);
		
		formaPagamento(pedidoModel);
		pedidoModel = pedidoRepository.save(pedidoModel);
		envioDeEmail(pedidoModel);
		
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
        	PedidoItemResponseDTO pedidoItemResponse = mapper.map(pdoItemRequest, PedidoItemResponseDTO.class);
        	pedidoItemResponse.setPedido(mapper.map(pedidoModel, PedidoResponseDTO.class));
        	
        	ProdutoResponseDTO prResponse = produtoService.obterPorId(pedidoItemResponse.getProduto().getId());	
        	pedidoItemResponse.setProduto(prResponse);

			controleEstoque(pedidoItemResponse);
			
			double vlTotalBruto = pedidoItemResponse.getProduto().getVlProduto() * pedidoItemResponse.getQtdPedidoitem();
			//PROMOÇÃO ATACADISTA
			if (pedidoItemResponse.getQtdPedidoitem() >= 5){
				pedidoItemResponse.setDescontoItem(vlTotalBruto * 0.05);
				pedidoModel.setDescontoPedido(pedidoModel.getDescontoPedido() + pedidoItemResponse.getDescontoItem());
			}

			double vlTotalItem = vlTotalBruto - pedidoItemResponse.getDescontoItem() + pedidoItemResponse.getAcrecimoItem();
        	pedidoItemResponse.setVltotalItem(vlTotalItem);
        	pedidoModel.setVltotalPedido(pedidoModel.getVltotalPedido()+vlTotalItem);
        	
        	pedidoItemResponse =  pedidoItemService.adicionar(mapper.map(pedidoItemResponse, PedidoItemRequestDTO.class));
        	prAdicionados.add(mapper.map(pedidoItemResponse, PedidoItem.class));
        }
        return prAdicionados;	
    }
	
    private void controleEstoque(PedidoItemResponseDTO pedidoItemResponse){
		if (pedidoItemResponse.getQtdPedidoitem() < 1){
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira uma quantidade válida. A quantidade deve ser igual ou maior que 1");
		}else if(pedidoItemResponse.getQtdPedidoitem() > pedidoItemResponse.getProduto().getQtdProduto() ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade solicitada é maior que a quantidade em estoque");
		}else{
			produtoService.atualizarQtd(pedidoItemResponse.getProduto(), pedidoItemResponse);
		}
	}

    private Pedido formaPagamento(Pedido pd) {

    	if(pd.getFormaPg().equals(FormaPagamento.BOLETO) || pd.getFormaPg().equals(FormaPagamento.PIX)) {
    		pd.setDescontoPedido(pd.getDescontoPedido() + (pd.getVltotalPedido() * 0.05));
    		pd.setVltotalPedido(pd.getVltotalPedido() - (pd.getVltotalPedido() * 0.05));
    		
    	}else if(pd.getFormaPg().equals(FormaPagamento.CARTAOCREDITO) || pd.getFormaPg().equals(FormaPagamento.CARTAODEBITO)){
    		pd.setAcrescimoPedido(pd.getAcrescimoPedido() + (pd.getVltotalPedido() * 0.05));
    		pd.setVltotalPedido(pd.getVltotalPedido() + (pd.getVltotalPedido()* 0.05));
    	}
    	return pd;
    }

	public ResponseEntity<?> envioDeEmail(Pedido pedido) {
		String destinatario = pedido.getUsuario().getEmail();
		String mensagem = gerarHTMLComDadosDosPedidos(pedido);	
		
		Email email = new Email("RESUMO PEDIDO JEMN", mensagem, "elton.medeiros14@gmail.com", destinatario);
		emailService.enviar(email);
        return ResponseEntity.status(200).body("E-mail enviado com sucesso!!!");
    }

    private String gerarHTMLComDadosDosPedidos(Pedido pedido) {
	StringBuilder html = new StringBuilder();

		// Tabela com os dados dos pedidos
		html.append("<div style=\"text-align: center;\">");
		html.append("<img src=\"https://i.postimg.cc/3wWD0Q6B/20003871-7ede-4cbc-abef-02ef1e7c0e57-removebg-preview.png\" alt=\"imagem-2023-10-18-235948700\" margin=0; height=25%; width=25%;>");
		html.append("<h1 style=\"color:#292325; text-align: center; font-family:Trebuchet MS; font-size:300%; \">" + "Olá" + " " + pedido.getUsuario().getNomeUsuario() + "!" + "</h1>"
		+ "<h1 style=\"color:#292325; text-align: center; font-family:Trebuchet MS; font-size:300%; \">" + "Seu pedido de Nº" + pedido.getId() + " " + "pago via " +  pedido.getFormaPg() + " " + "foi finalizado com sucesso!"  + "</h1>"
		+ "<table style=\"border-collapse: collapse; width: 100%; margin: 0 20px 0 20px;\">"
		+ "<tr>"
		+ "<th style=\"padding: 8px; border: 2px solid black; background-color: #2E272A; color:white; text-align: center; font-family:Trebuchet MS; font-size:150%;\">PRODUTOS COMPRADOS</th>"
		+ "<th style=\"padding: 8px; border: 2px solid black; background-color: #2E272A; color:white; text-align: center; font-family:Trebuchet MS; font-size:150%;\">QUANTIDADE</th>"
		+ "<th style=\"padding: 8px; border: 2px solid black; background-color: #2E272A; color:white; text-align: center; font-family:Trebuchet MS; font-size:150%;\">VALOR TOTAL DOS PRODUTOS</th>"
		+ "<th style=\"padding: 8px; border: 2px solid black; background-color: #2E272A; color:white; text-align: center; font-family:Trebuchet MS; font-size:150%;\">VALOR TOTAL COM DESCONTO</th>"
		+ "</tr>"
		+ "<br>");

		for (PedidoItem pedidoItem : pedido.getPedidoItens()) {
		html.append("<td style=\"padding: 8px; border: 2px solid black; background-color: #B3A085; text-align: center; font-family:Trebuchet MS; font-size:150%;\">" + pedidoItem.getProduto().getNomeProduto() + "</td>"
		+ "<td style=\"padding: 8px; border: 2px solid black; background-color: #B3A085; text-align: center; font-family:Trebuchet MS; font-size:150%;\" >" + pedidoItem.getQtdPedidoitem() + "</td>"
		+ "<td style=\"padding: 8px; border: 2px solid black; background-color: #B3A085; text-align: center; font-family:Trebuchet MS; font-size:150%;\"> R$" + pedidoItem.getProduto().getVlProduto() * pedidoItem.getQtdPedidoitem() + "</td>"
		+ "<td style=\"padding: 8px; border: 2px solid black; background-color: #B3A085; text-align: center; font-family:Trebuchet MS; font-size:150%;\"> R$" + pedidoItem.getVltotalItem() + "</td>"
		+ "</tr>");
		}

		html.append("</table>"
		+ "<h1 style=\"color:black; text-align: center;  font-family:Impact; font-size:250%;\">Valor total do pedido: R$" + pedido.getVltotalPedido() + "</h1>"			
		+ "<p><h3 style=\"color:#292325; text-align: center; font-family:Trebuchet MS; font-size:200%;\">Obrigado por sua compra!</h3></p>");

		return html.toString();
    }
}
