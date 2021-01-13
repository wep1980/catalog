package com.devwaldirep.catalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devwaldirep.catalog.service.exceptions.EntityNotFoundException;

/**
 * Classe que intercepta qualquer excessão que acorra na camada de Resource(Controlador REST)
 * @author wepbi
 *
 */
@ControllerAdvice // Controlador de excessão
public class ResourceExceptionHandler {
	
	
	/**
	 * Esse metodo intercepta o Resource(Controller)
	 * Esse metodo e uma resposta de requisição onde o conteudo da resposta vai ser o objeto StandardError(Resposta Customizada de acordo com POSTMAN)
	 * HttpServletRequest -> Objeto que tem as informações da requisição
	 */
	@ExceptionHandler(EntityNotFoundException.class) // Avisa qual e o tipo de excessão que sera interceptada
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value()); // NOT_FOUND -> e do Tipo Enum e o value() pega o numero inteiro dele
		err.setError("Resource not found");
		err.setMessage(e.getMessage()); // pegando a mensagem que foi definida no service
		err.setPath(request.getRequestURI()); // Pega o caminho da requisição feita no momento do erro
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err); // No corpo(body) da resposta vai o objeto err
	}

}
