package com.IES.AuthService;

import javax.validation.Valid;

import com.IES.DTO.LoginRequestDTO;
import com.IES.DTO.LoginResposneDTO;
import com.IES.DTO.SignUpDto;

public interface AuthenticationService {

	String signUp(@Valid SignUpDto sdtorequest);

	LoginResposneDTO login(@Valid LoginRequestDTO loginRequest);

}
