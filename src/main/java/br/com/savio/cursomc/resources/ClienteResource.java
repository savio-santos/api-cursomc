package br.com.savio.cursomc.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.savio.cursomc.domain.Cliente;
import br.com.savio.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService clienteService;

	
	@GetMapping
	 public List<Cliente> listar() {
		 return clienteService.findAll();
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {

			Optional<Cliente> obj = Optional.of(clienteService.find(id));

				return ResponseEntity.ok(obj.get());
	 }
	 
}
