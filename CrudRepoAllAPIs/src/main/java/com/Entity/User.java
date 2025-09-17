package com.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Entity
@Data
@Validated
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	
	@JsonProperty(value = "un")
	@NotBlank(message ="username should not blank")
	private String uname;
	
	@JsonProperty(value = "ua")
	@NotBlank(message ="uaddress should not blank")
	private String uaddress;
	
	@JsonProperty(value = "us")
	@NotNull(message = "Salary should not be null")
	@Positive(message = "Salary must be positive")
	private Double usalary;
	
	@JsonProperty(value = "uc")
	@NotNull(message = "Contact number should not be null")
	@Digits(integer = 10, fraction = 0, message = "Contact must be exactly 10 digits")
	private Long ucontact;
	
	@JsonProperty(value = "up")
	@NotBlank(message = "Password should not be blank")
	private String upassword;
	
}
