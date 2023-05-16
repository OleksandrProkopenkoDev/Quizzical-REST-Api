package com.spro.controller;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spro.registration.AppUserRegistrationRequest;
import com.spro.service.RegistrationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")

public class RegistrationController {

	private final RegistrationService registrationService;
	
	
	@PostMapping("registration")
	public ResponseEntity<String> registerUser(@RequestBody AppUserRegistrationRequest requestBody) {
		registrationService.registerUser(requestBody);
		System.out.println(requestBody);
	
	return ResponseEntity.ok("registred");
	}
}
