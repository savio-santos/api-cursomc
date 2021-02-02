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

import br.com.savio.cursomc.domain.Cidade;
import br.com.savio.cursomc.repositories.CidadeRepository;
import br.com.savio.cursomc.services.exceptions.MyDataIntegrityViolationException;
import br.com.savio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade find(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

	public List<Cidade> findAll() {
		return repo.findAll();
	}

	public List<Cidade> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}

	@Transactional
	public Cidade insert(Cidade obj) {

		return repo.save(obj);

	}

	public Cidade update(Cidade obj) {
		Cidade cidade = find(obj.getId());
		cidade.setNome(obj.getNome());
		return repo.save(obj);

	}

	public void delete(Integer id) {

		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new MyDataIntegrityViolationException(
					"Não é póssivel excluir um objeto com associaçoes cadastradas.");
		}
	}

	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
