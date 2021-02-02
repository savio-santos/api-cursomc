package br.com.savio.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.savio.cursomc.domain.Estado;
import br.com.savio.cursomc.dto.EstadoDTO;
import br.com.savio.cursomc.repositories.EstadoRepository;
import br.com.savio.cursomc.services.exceptions.MyDataIntegrityViolationException;
import br.com.savio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Estado find(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}

	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}

	@Transactional
	public Estado insert(Estado obj) {

		return repo.save(obj);

	}

	public Estado update(Estado obj) {
		Estado estado = find(obj.getId());
		estado.setNome(obj.getNome());
		return repo.save(obj);

	}

	public void delete(Integer id) {

		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			 throw new MyDataIntegrityViolationException("Não é póssivel excluir um objeto com associaçoes cadastradas.");
		}
	}

	public Page<Estado> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Estado fromDTO(EstadoDTO obj) {
		return new Estado(obj.getId(), obj.getNome());
	}

}
