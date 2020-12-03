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
import br.com.savio.cursomc.domain.Cliente;
import br.com.savio.cursomc.domain.Endereco;
import br.com.savio.cursomc.domain.enums.TipoCliente;
import br.com.savio.cursomc.dto.ClienteDTO;
import br.com.savio.cursomc.dto.ClienteNewDTO;
import br.com.savio.cursomc.repositories.ClienteRepository;
import br.com.savio.cursomc.repositories.EnderecoRepository;
import br.com.savio.cursomc.services.exceptions.MyDataIntegrityViolationException;
import br.com.savio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;

	}

	public Cliente update(Cliente obj) {
		Cliente cli = find(obj.getId());
		cli.setNome(obj.getNome());
		cli.setEmail(obj.getEmail());
		return clienteRepository.save(cli);

	}

	public void delete(Integer id) {

		try {
			find(id);
			clienteRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new MyDataIntegrityViolationException(
					"Não é póssivel excluir ppois estqa entidade possui associaçoes.");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;

	}

}
