package com.spro.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spro.entity.Question;
import com.spro.repository.QuestionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class QuestionService {
	
	private QuestionRepository questionRepo;
	
	public List<Question> saveListOfQuestions(List<Question> questions) {
		return questionRepo.saveAll(questions);
	}

	public List<Question> findRandomQuestionsBy(
			Integer amount, 
			String difficulty, 
			String category) {
//		System.out.println("amount="+amount+" difficulty="+difficulty+" category="+category);
		if(amount == null) amount = 5;
		if(amount < 1) amount = 1;
		if (amount > 10) amount = 10;
		
		List<Question> questions;
		
		if(difficulty == null && category == null) {
			List<Question> questionsAll = 
					questionRepo.findAll();
			questions = questionsAll;
		}else if(category == null) {
			List<Question> questionsByDifficulty = 
					questionRepo.findAllByDifficulty(difficulty);
			questions = questionsByDifficulty;
		}else if(difficulty == null) {	
			List<Question> questionsByCategory = 
					questionRepo.findAllByCategory(category);
			questions = questionsByCategory;
		}else {
			List<Question> questionsByDifficultyAndCategory = 
					questionRepo.findAllByDifficultyAndCategory(difficulty, category);
			questions = questionsByDifficultyAndCategory;
		}
		
//		questions.forEach(System.out::println);
		long maxId = questions.size();
		//total number of questions in DB
//		System.out.println("maxId = "+maxId);
		//generate random IDs array
		List<Integer> idsArray = generateRandomIdArray(amount, maxId);
//		idsArray.forEach(System.out::print);
		List<Question> resultList = new ArrayList<>();
		
		//fill resultList by questions from 'questionsByDifficultyAndCategory' list with random IDs array
		idsArray.forEach(
				(id)->resultList.add(questions.get(id)) );
		
//		resultList.forEach(System.out::println);
		
		//find questions using generated IDs array
		return resultList;
	}

	private List<Integer> generateRandomIdArray(int amount, long maxId) {
		//generate array of unique random IDs
		List<Integer> idsArray = new ArrayList<>();
		if(amount>maxId) amount = (int) maxId;
		for (int i = 0; i < amount; i++) {
			long prevId = -1;
			long nextId;
			if(i>0) {
				prevId = idsArray.get(i-1);
			}
			do {
				nextId = new Random().nextLong(maxId);
			}while(prevId == nextId);			
			idsArray.add((int) nextId);									
		}
		return idsArray;
	}

	
	public List<String> getAvailableCategories() {
		
		return questionRepo.findAllCategoriesDistinct();
	}

}
