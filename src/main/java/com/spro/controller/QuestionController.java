package com.spro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spro.entity.Question;
import com.spro.service.QuestionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/questions")
	public List<Question> addListOfQuestions(@RequestBody List<Question> questions) {
		for (Question question : questions) {
			System.out.println(question);			
		}		
		return questionService.saveListOfQuestions(questions);
	}
	
	@GetMapping("/questions")
	public List<Question> getQuestions(@RequestParam("amount") int amount ){	
		return questionService.findAmountOfRandomQuestions(amount);
	}
}
