package br.com.savio.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.savio.cursomc.domain.Cliente;
import br.com.savio.cursomc.dto.ClienteDTO;
import br.com.savio.cursomc.resources.exception.FieldMessage;
import br.com.savio.cursomc.services.ClienteService;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	ClienteService service;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); //pegando pathvariable no PUT  
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = service.findByEmail(objDto.getEmail()); 
	if(aux != null && !aux.getId().equals(uriId))
		list.add(new FieldMessage("Email","Este email já existe"));

for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}


	
}
