package br.com.savio.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.savio.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
