package com.spro.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spro.registration.AppUserRegistrationRequest;
import com.spro.service.RegistrationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping("/api/v1/registration")
@CrossOrigin(origins = {"*","http://localhost:3000"})
public class RegistrationController {

	private final RegistrationService registrationService;
	
	@PostMapping
	public String registerUser(@RequestBody AppUserRegistrationRequest requestBody) {
		registrationService.registerUser(requestBody);
		return "user registred successfully";
	}
}
