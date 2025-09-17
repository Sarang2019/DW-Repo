package com.IES.AuthServiceIMPL;

import java.time.Instant;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.IES.AuthService.AuthenticationService;
import com.IES.AuthService.JwtService;
import com.IES.DTO.LoginRequestDTO;
import com.IES.DTO.LoginResposneDTO;
import com.IES.DTO.SignUpDto;
import com.IES.Entity.User;
import com.IES.Enum.Role;
import com.IES.Exception.PasswordInvalid;
import com.IES.Exception.UserAlreadyExist;
import com.IES.Exception.UserNotExistWithEmail;
import com.IES.Repos.AuthenticationRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceIMPL implements AuthenticationService {

	@Autowired
	private AuthenticationRepo authRepo;

	@Autowired
	private BCryptPasswordEncoder passencode;

	@Autowired
	private AuthenticationManager AuthenticationManager;

	@Autowired
	private JwtService jwtService;

	@Override
	public String signUp(@Valid SignUpDto sdtorequest) {

		if (authRepo.findByEmail(sdtorequest.getEmail()) != null) {
			throw new UserAlreadyExist("user alredy Registerd");
//			return "user alredy Registerd";
		}

		User user = new User();

		ModelMapper model = new ModelMapper();
		User user1 = model.map(sdtorequest, User.class);
		user1.setRole(Role.Citizne);
		user1.setPwd(passencode.encode(sdtorequest.getPwd()));
		authRepo.save(user1);

		return "User Registerd sucessfully";
	}

	@Override
	public LoginResposneDTO login(@Valid LoginRequestDTO loginRequest) {

		User user1 = authRepo.findByEmail(loginRequest.getEmail());
		if (user1 == null) {
			// custome Exception
			log.warn("Email Invalid  , User not Found with this email");
			throw new UserNotExistWithEmail("Email Invalid  , User not Found with this email");

		}

		if (!passencode.matches(loginRequest.getPwd(), user1.getPwd())) {
			throw new PasswordInvalid("Password Incorrect!!");
		}

		AuthenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPwd()));

		Instant currentDate = Instant.now();

		String token = jwtService.generateToken(user1);

		LoginResposneDTO lrdto = new LoginResposneDTO();

		ModelMapper model = new ModelMapper();

		LoginResposneDTO loginResponse = model.map(user1, LoginResposneDTO.class);

		loginResponse.setToken(token);

		return loginResponse;

	}
}
