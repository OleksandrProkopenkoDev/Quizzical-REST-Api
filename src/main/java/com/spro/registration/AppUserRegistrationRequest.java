package com.spro.registration;

public record AppUserRegistrationRequest(
		String username,
		String nickname,
		String password
		) {	
}
