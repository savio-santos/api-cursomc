package br.com.savio.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.savio.cursomc.dto.EmailDTO;
import br.com.savio.cursomc.security.JWTUtil;
import br.com.savio.cursomc.security.UserSS;
import br.com.savio.cursomc.services.AuthService;
import br.com.savio.cursomc.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService authService;

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/forgot")
	public ResponseEntity<Void> forgotPass(@Valid @RequestBody EmailDTO obj) {
		authService.sendNewPassword(obj.getEmail());

		return ResponseEntity.noContent().build();
	}
}
