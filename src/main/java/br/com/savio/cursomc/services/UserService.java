package br.com.savio.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.savio.cursomc.security.UserSS;

public class UserService {

	public static UserSS authenticated() { //metodo que retorna um usuario logado
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
