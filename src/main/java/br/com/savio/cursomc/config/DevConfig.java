package br.com.savio.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.savio.cursomc.services.DBService;
import br.com.savio.cursomc.services.EmailService;
import br.com.savio.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatebase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}

			dbService.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean// disponibiliza como um componente do sistema para injeçoes de dependecia
	public EmailService emailService() {
		return new SmtpEmailService(); 
	}
}

