package com.spro.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spro.auth.AuthRequest;
import com.spro.security.AppUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class JwtUsernameAndPasswordAuthenticationFilter 
					extends UsernamePasswordAuthenticationFilter {


	private final AuthenticationManager authenticationManager;
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response)
			throws AuthenticationException {
		//verifies credentials
		try {
			AuthRequest authRequest = new ObjectMapper()
						.readValue(request.getInputStream(), AuthRequest.class);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				authRequest.username(), 
				authRequest.password());
			 Authentication authenticate = authenticationManager.authenticate(authentication);
			 return authenticate;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	//this method will be invoked after attempt authentication is succesfull
	// we will create token here. If auth fails method not invoked
	@Override 
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		AppUser authUser = (AppUser)authResult.getPrincipal();
		String token = Jwts.builder()
			.setSubject(authResult.getName())
			.claim("userId", authUser.getId())
			.claim("nickname", authUser.getNickname())
			.setIssuedAt(new Date())
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
			.signWith(secretKey)
			.compact();
		response.addHeader(
				jwtConfig.getAuthorizationHeader(), 
				jwtConfig.getTokenPrefix()+token );
//		next filter
		
	}
	
	
	
	
}
