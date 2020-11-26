package br.com.savio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.savio.cursomc.domain.Pedido;
import br.com.savio.cursomc.repositories.PedidoRepository;
import br.com.savio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public void insert(Pedido obj) {
		pedidoRepository.save(obj);

	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

}
