package com.spro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spro.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long>{

	List<Result> findAllByUserId(Long userId);
	
//	List<Result> findAllByUsername(String username);

}
