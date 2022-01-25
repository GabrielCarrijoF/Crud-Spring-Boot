package com.crud.crudlogin.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.crudlogin.service.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> notFound(EntityNotFoundException entityNotFoundException, HttpServletRequest httpServletRequest){
		
		StandardError standardError = new StandardError();
		standardError.setTimesStamp(Instant.now());
		standardError.setStatus(HttpStatus.NOT_FOUND.value());
		standardError.setError("Recurso Não Encontrado");  //Configuração da mensagem de erro utilizando Advice
		standardError.setMenssage(entityNotFoundException.getMessage());
		standardError.setPath(httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
}
