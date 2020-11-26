package br.com.savio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.savio.cursomc.domain.Categoria;
import br.com.savio.cursomc.repositories.CategoriaRepository;
import br.com.savio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	
	public Categoria insert(Categoria obj) {
		find(obj.getId());
		
		return repo.save(obj);

	}
	
	public Categoria update(Categoria obj) {
		
		return repo.save(obj);

	}

	

}
