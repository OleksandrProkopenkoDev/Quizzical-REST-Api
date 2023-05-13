package com.spro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spro.security.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	Optional<AppUser> findByUsername(String username);
	
	@Query(value = "SELECT DISTINCT nickname FROM users WHERE id = ?1", 
		   nativeQuery = true)
	Optional<String> findNicknameById(Long id);

}
