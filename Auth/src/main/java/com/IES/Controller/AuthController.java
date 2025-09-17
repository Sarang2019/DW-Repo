package com.IES.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties.OkHttp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IES.DTO.ForgetPassDTO;
import com.IES.DTO.LoginRequestDTO;
import com.IES.DTO.signUpDto;
import com.IES.Service.ServiceIES;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name = "this is authentication api")
@RequestMapping("/ies/v1/auth")
public class AuthController {

	@Autowired
	private ServiceIES AuthService;

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@Valid @RequestBody signUpDto rdt) {
		System.out.println(rdt);
		log.info("Controller layer ::" + rdt);
		log.debug("Debug signUpDto value :: " + rdt);
		return new ResponseEntity(AuthService.string(rdt), HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn( @Valid @RequestBody LoginRequestDTO loginRequest){
		log.info("Controller login ::" + loginRequest);
		log.debug("Debug LoginrequestDTO value :: " + loginRequest);
		
		return new ResponseEntity(AuthService.login(loginRequest),HttpStatus.OK);
	}

	
	@PutMapping("/forgetPass")
	public ResponseEntity<?>ForgetPass(@Valid @RequestBody ForgetPassDTO fpd ){
		log.info("Controller layer ::" + fpd);
		log.debug("Debug ForgetPass value :: " + fpd);
		return new ResponseEntity( AuthService.ForgetPassInService(fpd) ,HttpStatus.OK);
	}

}
