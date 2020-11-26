package br.com.savio.cursomc.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.savio.cursomc.domain.Pedido;
import br.com.savio.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public List<Pedido> listar() {
		return pedidoService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {

		Optional<Pedido> obj = Optional.of(pedidoService.find(id));

		return ResponseEntity.ok(obj.get());
	}

}
