package br.com.savio.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.savio.cursomc.domain.Cliente;
import br.com.savio.cursomc.repositories.ClienteRepository;
import br.com.savio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;

	Random rand = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {
			throw new ObjectNotFoundException("email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);

	}

	
	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {

		int opt = rand.nextInt(3);

		if (opt == 0) {// gera um digito
			return (char) (rand.nextInt(10) + 48);

		} else if (opt == 1) {// letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		
		} else {// minuscula
			return (char) (rand.nextInt(26) + 65);
		}

	}

}
