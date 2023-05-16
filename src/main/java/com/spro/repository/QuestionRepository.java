package com.spro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spro.entity.Question;


public interface QuestionRepository extends JpaRepository<Question, Long>{

	List<Question> findAllByDifficultyAndCategory(String difficulty, String category);

	List<Question> findAllByDifficulty(String difficulty);

	List<Question> findAllByCategory(String category);
	
	@Query(value = "SELECT DISTINCT category FROM questions;",
			   nativeQuery = true)
		List<String> findAllCategoriesDistinct();
}
