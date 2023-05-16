package com.spro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spro.entity.RatingRecord;

public interface RatingRecordRepository 
			extends JpaRepository<RatingRecord, Long>{

	Optional<RatingRecord> findByUserId(Long userId);
}
