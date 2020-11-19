package br.com.savio.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.savio.cursomc.domain.Categoria;
import br.com.savio.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		return repo.getOne(id);
	
		
	}

	public void persist(Categoria obj) {
		repo.save(obj);
		
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	
}
