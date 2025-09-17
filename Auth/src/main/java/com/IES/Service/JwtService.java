package com.IES.Service;

import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import com.IES.Security.CustomerUserService;

import io.jsonwebtoken.Claims;

public interface JwtService {
	String extractUsername(String token);

	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);

	String generateToken(CustomerUserService userDetails);

	String generateToken(Map<String, Object> extraClaims, CustomerUserService userDetails);

	long getExpirationTime();

	boolean isTokenValid(String token, CustomerUserService userDetails);

	String extractTokenFromRequest(HttpServletRequest request);

	

}
