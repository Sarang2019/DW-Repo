package com.IES.Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ResponseEntity<?> handleUserAlradyExist() {
		log.warn("User Already Exist with this given email ");
		return new ResponseEntity("User Already Exist", HttpStatus.OK);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleAPiValidationsExceptions(MethodArgumentNotValidException ex) {
		log.warn("payload is worong");
		List<String> errors = new ArrayList();
		ex.getBindingResult().getFieldErrors().forEach(error -> {

			errors.add(error.getDefaultMessage());
		});
		return new ResponseEntity(errors, HttpStatus.OK);
	}

	@ExceptionHandler(value = UserNotExistWithEmail.class)
	public ResponseEntity<?> userNotExistWithEmail() {
		log.warn("User not exist ");
		return new ResponseEntity("Email is invalid", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = PasswordInvalid.class)
	public ResponseEntity<?> passwordInvalid() {
		log.warn("passwor incorrect");
		return new ResponseEntity("password incorrect", HttpStatus.BAD_REQUEST);
	}
}
