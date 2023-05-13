package com.spro.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spro.auth.AuthRequest;
import com.spro.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = {"*","http://localhost:3000"})
public class AuthController {

	private final AuthService authService;
	
	@PostMapping
	public String authenticate(@RequestBody AuthRequest requestBody) {
		return authService.authanticate(requestBody);
		
	}
}
