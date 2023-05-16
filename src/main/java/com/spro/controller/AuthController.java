package com.spro.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spro.auth.AuthRequest;
import com.spro.dto.AuthResponse;
import com.spro.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("auth")
	public AuthResponse authenticate(@RequestBody AuthRequest requestBody) {
		System.out.println("auth request with body: "+requestBody);
		return authService.authanticate(requestBody);
		
	}
}
