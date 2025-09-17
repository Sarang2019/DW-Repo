package com.IES.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.IES.Enum.Role;

import lombok.Data;

@Data
public class signUpDto {

	@Column(name = "name",nullable = false,length = 100)
	@NotBlank(message = "name is required")
	@Size(min = 2,max = 100,message = "name must be 3-100 char")
	private String name;
	
	
	@Column(name = "email",unique = true,nullable = false,length = 150)
	@Email(message = "invalid email format")
	@NotBlank(message = "email is required")
	private String email;
	
	@Column(name = "phno")
	@NotNull(message = "phone number required")
	@Digits(integer = 10,fraction = 0,message = "phone number must be 10 digit")
	private long phno;
	
	@Column(name = "pwd",nullable = false)
	@NotBlank(message = "password required")
	@Size(min = 6,message = "password must be at least 6 character")
	private String pwd;
	
}
