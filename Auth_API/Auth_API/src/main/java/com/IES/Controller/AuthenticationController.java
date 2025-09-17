package com.IES.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IES.AuthService.AuthenticationService;
import com.IES.DTO.LoginRequestDTO;
import com.IES.DTO.SignUpDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name = "This is Authntcation API")
@RequestMapping("/ies/v1/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDto sdtorequest) {
		log.info("Controller Layer ::" + sdtorequest);
		log.debug("Debug SignUpDto Value :: " + sdtorequest);

		return new ResponseEntity(authService.signUp(sdtorequest), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequestDTO loginRequest) {
		log.info("Controller Login :: " + loginRequest);
		log.debug("Debug LoginRequesDTO value :: " + loginRequest);

		return new ResponseEntity(authService.login(loginRequest), HttpStatus.OK);
	}
	public void trial() {
		System.out.println("This is trial for git perfection");
	}
}
