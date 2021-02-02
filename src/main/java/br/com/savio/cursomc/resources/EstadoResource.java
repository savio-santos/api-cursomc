package br.com.savio.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.savio.cursomc.domain.Estado;
import br.com.savio.cursomc.dto.CidadeDTO;
import br.com.savio.cursomc.dto.EstadoDTO;
import br.com.savio.cursomc.services.CidadeService;
import br.com.savio.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	EstadoService estadoService;

	@Autowired
	CidadeService cidadeService;

	@GetMapping("/{id}")
	public ResponseEntity<Estado> find(@PathVariable Integer id) {

		Estado obj = estadoService.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> list = estadoService.findAll().stream().map(obj -> new EstadoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<CidadeDTO> list = cidadeService.findByEstado(estadoId).stream().map(obj -> new CidadeDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Estado estado) {
		estado = estadoService.insert(estado);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody @Valid EstadoDTO objDto) {
		Estado obj = estadoService.fromDTO(objDto);
		obj.setId(id);
		obj = estadoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> delete(@PathVariable Integer id) {
		estadoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
