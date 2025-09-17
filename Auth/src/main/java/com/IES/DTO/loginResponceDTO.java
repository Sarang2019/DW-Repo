package com.IES.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class loginResponceDTO {

	
	@NotBlank(message = "name is required")
	@Size(min = 2,max = 100,message = "name must be 3-100 char")
	private String name;
	
	
	@Email(message = "invalid email format")
	@NotBlank(message = "email is required")
	private String email;
	
	private String token;
}
