package br.com.savio.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.savio.cursomc.services.DBService;
import br.com.savio.cursomc.services.EmailService;
import br.com.savio.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatebase() throws ParseException {

		dbService.instantiateTestDatabase();

		return true;
	}

	@Bean // disponibiliza como um componente do sistema para injeçoes de dependecia
	public EmailService emailService() {
		return new MockEmailService();

	}
}
