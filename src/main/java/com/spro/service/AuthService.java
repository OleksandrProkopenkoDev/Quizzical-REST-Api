package com.spro.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spro.auth.AuthRequest;
import com.spro.dto.AuthResponse;
import com.spro.security.AppUser;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;

	public AuthResponse authanticate(AuthRequest requestBody) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						requestBody.username(),
						requestBody.password())
				);//if auth failed, redirecting to login. Code below is not run
		SecurityContextHolder.getContext().setAuthentication(auth);
		AppUser authUser = (AppUser)auth.getPrincipal();
		
		return new AuthResponse(authUser.getId(), authUser.getNickname());
	}

}
