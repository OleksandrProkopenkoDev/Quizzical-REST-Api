package com.spro.controller;

import java.util.List;

import com.spro.dto.QuestionDto;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping("/api/v1")
public class QuestionController {

	private Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionService questionService;

	@PostMapping("post")
	public List<QuestionDto> addListOfQuestions(
			@RequestBody List<QuestionDto> questions) {
		return questionService.saveListOfQuestions(questions);
	}

	@GetMapping("questions")
	public List<QuestionDto> getQuestionsBy(@RequestParam Map<String, String> requestParams){
		logger.info("Received getQuestionsList request with params "+requestParams);
		return questionService.findRandomQuestionsBy(requestParams);
	}
	
	@GetMapping("categories")
	public List<String> getAvailableCategories(){
		return questionService.getAvailableCategories();
	}
	
}





//	@GetMapping("questions")
//	public List<QuestionDto> getQuestionsByAmount(
//			@RequestParam(value = "amount") Integer amount){
//		return questionService.findRandomQuestionsByAmount(amount);
//	}

//	@GetMapping("questions")
//	public List<QuestionDto> getQuestionsByDifficulty(
//			@RequestParam(value = "difficulty") String difficulty
//			 ){
//		return questionService.findRandomQuestionsByDifficulty(difficulty);
//	}

//	@GetMapping("questions")
//	public List<QuestionDto> getQuestionsByCategory(
//			@RequestParam(value = "category")String category ){
//		return questionService.findRandomQuestionsByCategory(category);
//	}

//	@GetMapping("questions")
//	public List<QuestionDto> getQuestionsByAmountAndDifficulty(
//			@RequestParam(value = "amount") Integer amount,
//			@RequestParam(value = "difficulty") String difficulty ){
//		return questionService.findRandomQuestionsByAmountAndDifficulty(amount, difficulty);
//	}
//	@GetMapping("questions")
//	public List<QuestionDto> getQuestionsByAmountAndCategory(
//			@RequestParam(value = "amount") Integer amount,
//			@RequestParam(value = "category")String category ){
//		return questionService.findRandomQuestionsByAmountAndCategory(amount, category);
//	}

//	@GetMapping("questions")
//	public List<QuestionDto> getQuestionsByAmountDifficultyAndCategory(
//			@RequestParam(value = "amount") Integer amount,
//			@RequestParam(value = "difficulty") String difficulty,
//			@RequestParam(value = "category")String category ){
//		return questionService.findRandomQuestionsByAmountDifficultyAndCategory(amount, difficulty, category);
//	}