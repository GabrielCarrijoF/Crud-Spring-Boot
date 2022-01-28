package com.crud.crudlogin.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.crudlogin.service.exceptions.DataBaseException;
import com.crud.crudlogin.service.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> notFound(ResourceNotFoundException entityNotFoundException, HttpServletRequest httpServletRequest){
		
		HttpStatus statusException = HttpStatus.NOT_FOUND; //Erro de requisição
		StandardError standardError = new StandardError();
		
		standardError.setTimesStamp(Instant.now());
		standardError.setStatus(statusException.value());
		standardError.setError("Recurso Não Encontrado");  //Configuração da mensagem de erro utilizando Advice
		standardError.setMenssage(entityNotFoundException.getMessage());
		standardError.setPath(httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(statusException).body(standardError);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> data(DataBaseException dataBaseException, HttpServletRequest httpServletRequest) {
		
		HttpStatus statusException = HttpStatus.BAD_REQUEST; //Erro de requisição
		StandardError standardError = new StandardError();
		
		standardError.setTimesStamp(Instant.now());
		standardError.setStatus(statusException.value());
		standardError.setError("Database exception"); 
		standardError.setMenssage(dataBaseException.getMessage());
		standardError.setPath(httpServletRequest.getRequestURI());
		
		return ResponseEntity.status(statusException).body(standardError);
	}
}