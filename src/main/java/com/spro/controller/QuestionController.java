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

@CrossOrigin(origins = {"*","http://localhost:3000"})
@RestController
@RequestMapping("/api/v1")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("post")
	public List<Question> addListOfQuestions(
			@RequestBody List<Question> questions) {
		return questionService.saveListOfQuestions(questions);
	}
	
	@GetMapping("questions")
	public List<Question> getQuestions(
		@RequestParam(value = "amount", required = false) int amount,
		@RequestParam(value = "difficulty",required = false) String difficulty,
		@RequestParam(value = "category",required = false)String category ){	
		return questionService.findRandomQuestionsBy(amount, difficulty, category);
	}
	
	@GetMapping("categories")
	public List<String> getAvailableCategories(){
		return questionService.getAvailableCategories();
	}
	
}
