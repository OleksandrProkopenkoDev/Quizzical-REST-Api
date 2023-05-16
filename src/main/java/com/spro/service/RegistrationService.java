package com.spro.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spro.registration.AppUserRegistrationRequest;
import com.spro.repository.AppUserRepository;
import com.spro.security.AppUser;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class RegistrationService {

	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;

	public void registerUser(AppUserRegistrationRequest requestBody) {
		appUserRepository.save(
				new AppUser(
						requestBody.username(), 
						requestBody.nickname(), 
						passwordEncoder.encode( requestBody.password()) ));
	}

	
}
