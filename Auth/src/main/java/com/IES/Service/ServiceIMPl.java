package com.IES.Service;

import java.time.Instant;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IES.DTO.ForgetPassDTO;
import com.IES.DTO.LoginRequestDTO;
import com.IES.DTO.loginResponceDTO;
import com.IES.DTO.signUpDto;
import com.IES.Entity.User;
import com.IES.Enum.Role;
import com.IES.Exception.InvalidTokenException;
import com.IES.Exception.PasswordInvalid;
import com.IES.Exception.UserAlreadyExist;
import com.IES.Exception.userNotExistwitheamil;
import com.IES.Repo.AuthRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceIMPl implements ServiceIES {
	@Autowired
	private PasswordEncoder passencode;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthRepo repo;

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public String string(@Valid signUpDto rdt) {

		if (repo.findByEmail(rdt.getEmail()) != null) {
			throw new UserAlreadyExist("User alredy registerd");
//			return "User already registered";
		}

		ModelMapper mp = new ModelMapper();
		User user = mp.map(rdt, User.class);
		user.setRole(Role.citizen);
		user.setPwd(passencode.encode(rdt.getPwd()));

		repo.save(user);
		return "user registerd success";
	}

	@Override
	public loginResponceDTO login(LoginRequestDTO loginRequest) {

		User user1 = repo.findByEmail(loginRequest.getEmail());
		if (user1 == null) {
			log.warn("Email Invalid, user not found with this email");
			throw new userNotExistwitheamil("email Invalid,user  not found with this mail");

		}
		if (!passencode.matches(loginRequest.getPwd(), user1.getPwd())) {
			log.warn("password incorrect!!");
			throw new PasswordInvalid("password Invalid,user  not found with this password");
		}

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPwd()));
		Instant currentdate = Instant.now();
		User user2 = repo.save(user1);
		String generateToken = jwtService.generateToken(user2);

		ModelMapper mp = new ModelMapper();
		loginResponceDTO loginResponce = mp.map(user2, loginResponceDTO.class);

		loginResponce.setToken(generateToken);

		return loginResponce;
	}

	@Override
	public String ForgetPassInService(ForgetPassDTO fpd) {
		User user1 = repo.findByEmail(fpd.getEmail());
		if (user1 == null) {
			log.warn("Email Invalid, user not found with this email");
			throw new userNotExistwitheamil("email Invalid,user  not found with this mail");

		}

		user1.setPwd(fpd.getPwdUpdated()); // update main password
		repo.save(user1);
		return "password updated successfully";
	}

}
