package com.IES.Service;

import javax.validation.Valid;

import org.springframework.util.MultiValueMap;

import com.IES.DTO.ForgetPassDTO;
import com.IES.DTO.LoginRequestDTO;
import com.IES.DTO.loginResponceDTO;
import com.IES.DTO.signUpDto;

public interface ServiceIES {

	String string(@Valid signUpDto rdt);

	loginResponceDTO login(@Valid LoginRequestDTO loginRequest);

	String ForgetPassInService(@Valid ForgetPassDTO fpd);

}
