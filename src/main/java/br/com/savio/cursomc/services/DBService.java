package br.com.savio.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.savio.cursomc.domain.Categoria;
import br.com.savio.cursomc.domain.Cidade;
import br.com.savio.cursomc.domain.Cliente;
import br.com.savio.cursomc.domain.Endereco;
import br.com.savio.cursomc.domain.Estado;
import br.com.savio.cursomc.domain.ItemPedido;
import br.com.savio.cursomc.domain.Pagamento;
import br.com.savio.cursomc.domain.PagamentoComBoleto;
import br.com.savio.cursomc.domain.PagamentoComCartao;
import br.com.savio.cursomc.domain.Pedido;
import br.com.savio.cursomc.domain.Produto;
import br.com.savio.cursomc.domain.enums.EstadoPagamento;
import br.com.savio.cursomc.domain.enums.Perfil;
import br.com.savio.cursomc.domain.enums.TipoCliente;
import br.com.savio.cursomc.repositories.CategoriaRepository;
import br.com.savio.cursomc.repositories.CidadeRepository;
import br.com.savio.cursomc.repositories.ClienteRepository;
import br.com.savio.cursomc.repositories.EnderecoRepository;
import br.com.savio.cursomc.repositories.EstadoRepository;
import br.com.savio.cursomc.repositories.ItemPedidoRepository;
import br.com.savio.cursomc.repositories.PagamentoRepository;
import br.com.savio.cursomc.repositories.PedidoRepository;
import br.com.savio.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	CategoriaRepository catRpository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	PagamentoRepository pagamentoRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		Categoria cat3 = new Categoria(null, "cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoracao");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritorio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		catRpository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));

		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));

		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Sávio Santos", "saviosantos276@gmail.com", "36378912377",
				TipoCliente.PESSOAFISICA, pe.encode("123"));

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		cli1.addPerfil(Perfil.ADMIN);

		Cliente cli2 = new Cliente(null, "Kauana Marques", "kauanamarques73@gmail.com", "75301158040",
				TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "rua Flores", "300", "apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", cli1, c2);

		Endereco e3 = new Endereco(null, "Avenida Biscoito", "110", null, "Centro", "41205321", cli2, c3);

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pedido ped3 = new Pedido(null, sdf.parse("01/01/2021 19:35"), cli2, e3);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				sdf.parse("25/10/2017 00:00"));

		Pagamento pagto3 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped3, 1);

		ped1.setPagamento(pagto1);

		ped2.setPagamento(pagto2);

		ped3.setPagamento(pagto3);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		cli2.getPedidos().addAll(Arrays.asList(ped3));

		pedidoRepository.saveAll((Arrays.asList(ped1, ped2, ped3)));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2, pagto3));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		ItemPedido ip4 = new ItemPedido(ped3, p1, 0.00, 2, 2000.00);
		ItemPedido ip5 = new ItemPedido(ped3, p3, 0.00, 2, 80.00);

		ped1.getItens().addAll((Arrays.asList(ip1, ip2)));
		ped2.getItens().addAll((Arrays.asList(ip3)));
		ped2.getItens().addAll((Arrays.asList(ip4, ip5)));

		p1.getItens().addAll(Arrays.asList(ip1, ip4));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2, ip5));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4, ip5));
	}
}
