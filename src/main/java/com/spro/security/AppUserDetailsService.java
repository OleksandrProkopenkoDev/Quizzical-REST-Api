package com.spro.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spro.repository.AppUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AppUserDetailsService implements UserDetailsService{

	private final AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		return appUserRepository.findByUsername(username)
					.orElseThrow(()->new UsernameNotFoundException("Username "+username+" not found"));
	}

}
