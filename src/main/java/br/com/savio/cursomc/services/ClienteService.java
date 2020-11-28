package br.com.savio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.savio.cursomc.domain.Cliente;
import br.com.savio.cursomc.dto.ClienteDTO;
import br.com.savio.cursomc.repositories.ClienteRepository;
import br.com.savio.cursomc.services.exceptions.MyDataIntegrityViolationException;
import br.com.savio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

		
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente insert(Cliente obj) {

		return repo.save(obj);

	}

	public Cliente update(Cliente obj) {
		Cliente cli = find(obj.getId());
		cli.setNome(obj.getNome());
		cli.setEmail(obj.getEmail());
		return repo.save(cli);

	}

	public void delete(Integer id) {

		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new MyDataIntegrityViolationException("Não é póssivel excluir ppois estqa entidade possui associaçoes.");
		}
	}


	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null);
	}

}
