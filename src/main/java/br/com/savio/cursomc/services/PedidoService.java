package br.com.savio.cursomc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDETE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		pedidoRepository.save(obj);
		pgtoRepository.save(obj.getPagamento());

		for (ItemPedido item : obj.getItens()) {
			item.setDesconto(0d);
			item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
			item.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

}
