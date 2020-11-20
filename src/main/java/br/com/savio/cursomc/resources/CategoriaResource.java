package br.com.savio.cursomc.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.savio.cursomc.domain.Categoria;
import br.com.savio.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService categoriaService;
	

	
	@GetMapping
	 public List<Categoria> listar() {
		 return categoriaService.findAll();
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<Categoria> buscar(@PathVariable Integer id) {

			Optional<Categoria> obj = Optional.of(categoriaService.find(id));

				return ResponseEntity.ok(obj.get());
	 }

		
		
	 
	 
}
