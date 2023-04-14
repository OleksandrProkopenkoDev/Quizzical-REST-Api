package com.spro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spro.entity.Question;


public interface QuestionService {

	List<Question> saveListOfQuestions(List<Question> questions);

	List<Question> findAmountOfRandomQuestions(int amount);
	
}
