package com.IES.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class LoginRequestDTO {

	@Column(name = "email",unique = true,nullable = false,length = 150)
	@Email(message = "invalid email format")
	@NotBlank(message = "email is required")
	private String email;
	
	
	@Column(name = "pwd",nullable = false)
	@NotBlank(message = "password required")
	@Size(min = 6,message = "password must be at least 6 character")
	private String pwd;
	
}
