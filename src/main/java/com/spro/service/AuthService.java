package com.spro.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spro.auth.AuthRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;

	public String authanticate(AuthRequest requestBody) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						requestBody.username(),
						requestBody.password())
				);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return auth.isAuthenticated()? "auth success": "auth failed";
	}

}
