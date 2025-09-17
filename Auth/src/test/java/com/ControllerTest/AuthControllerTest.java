package com.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.IES.Controller.AuthController;
import com.IES.DTO.signUpDto;
import com.IES.Service.ServiceIES;

@SpringBootTest
public class AuthControllerTest {

	@Mock
	private ServiceIES service;

	@InjectMocks
	private AuthController authcontroller;

	public void setup() {

		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void TestSignUp() {
		signUpDto sto = new signUpDto();

		sto.setEmail("xyz@gamil.com");

		String value = "user registerd success";

		when(service.string(sto)).thenReturn(value);

		ResponseEntity<?> result = authcontroller.signUp(sto);

		assertEquals(value, result.getBody());
		assertEquals(org.springframework.http.HttpStatus.OK, result.getStatusCode());
	}

}
