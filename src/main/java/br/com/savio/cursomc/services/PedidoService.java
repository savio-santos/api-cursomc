package br.com.savio.cursomc.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.savio.cursomc.domain.ItemPedido;
import br.com.savio.cursomc.domain.PagamentoComBoleto;
import br.com.savio.cursomc.domain.Pedido;
import br.com.savio.cursomc.domain.enums.EstadoPagamento;
import br.com.savio.cursomc.repositories.ItemPedidoRepository;
import br.com.savio.cursomc.repositories.PagamentoRepository;
import br.com.savio.cursomc.repositories.PedidoRepository;
import br.com.savio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	PagamentoRepository pgtoRepository;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		Calendar cal = Calendar.getInstance();
		
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		obj=pedidoRepository.save(obj);
		pgtoRepository.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

}
