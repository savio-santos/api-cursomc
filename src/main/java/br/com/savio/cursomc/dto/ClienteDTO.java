package br.com.savio.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.savio.cursomc.domain.Cliente;
import br.com.savio.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Length(min = 5,max = 120,message = "Não pode ser nulo e o minimo 5 maximo 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Email(message = "Email invalido")
	private String email;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente obj) {
		this.email = obj.getEmail();
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
