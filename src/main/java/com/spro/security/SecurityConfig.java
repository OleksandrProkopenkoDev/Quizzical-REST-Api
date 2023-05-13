package com.spro.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider authenticationProvider) throws Exception {
        http
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers(
                HttpMethod.GET,
                "/api/v1/ratingtable" //,"/api/v1/auth/login"
                
        )
        .permitAll()
        .requestMatchers(
                HttpMethod.POST,
                "/api/v1/registration",
                "/api/v1/auth"
        )
        .permitAll()
        .requestMatchers(
                HttpMethod.GET,
                "/api/v1/questions",
                "/api/v1/categories"
        )
        .permitAll()
//        .requestMatchers(HttpMethod.GET, "/actuator/**")
//        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .authenticationProvider(authenticationProvider)
        .formLogin()
        .and()
        .httpBasic();
 
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration)
					throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder
			) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
}
