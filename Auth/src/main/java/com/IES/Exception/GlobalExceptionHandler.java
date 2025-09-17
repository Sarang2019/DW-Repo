package com.IES.Exception;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.internal.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(value = UserAlreadyExist.class)
	public ResponseEntity<?> HandleUserAlreadyExist() {
		log.warn("user alredy exist with this given email");
		return new ResponseEntity("user already exist", HttpStatus.OK);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> HandleApiValidationExceptions(MethodArgumentNotValidException ex) {
		log.warn("payload is wrong");
		List<String> errors = new ArrayList();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.add(error.getDefaultMessage());
		});

		return new ResponseEntity(errors, HttpStatus.OK);
	}

	@ExceptionHandler(value = userNotExistwitheamil.class)
	public ResponseEntity<?> HandleuserNotExistwitheamil() {
		log.warn("user not exist with this given email");
		return new ResponseEntity("user not  exist", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = PasswordInvalid.class)
	public ResponseEntity<?> HandlePassInvalid() {
		log.warn("user password is not matched ");
		return new ResponseEntity("user pass not match", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidTokenException.class)
	public ResponseEntity<?> HandleInvalidTokenException() {
		log.warn("token is not matched ");
		return new ResponseEntity("Token not match", HttpStatus.BAD_REQUEST);
	}

}
