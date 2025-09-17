package com.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	//predefine exception handling
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception ex){
		
		log.error("Message :" + ex.getMessage() );
		
		return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException u){
		
		log.error("Message :" + u.getMessage());
		
		return new ResponseEntity(u.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException u){
		
		log.error("Message :" + u.getMessage());
		
		return new ResponseEntity(u.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
