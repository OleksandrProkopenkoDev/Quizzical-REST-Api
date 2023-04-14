package com.spro.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spro.entity.Question;
import com.spro.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionRepository questionRepo;
	


	@Override
	public List<Question> saveListOfQuestions(List<Question> questions) {
		
		return questionRepo.saveAll(questions);
	}



	@Override
	public List<Question> findAmountOfRandomQuestions(int amount) {
		if(amount < 1) amount = 1;
		if (amount > 10) amount = 10;
		List<Long> idsArray = new ArrayList<>();
		long maxId = questionRepo.count();
		for (int i = 0; i < amount; i++) {
			long prevId = -1;
			long nextId;
			if(i>0) {
				prevId = idsArray.get(i-1);
			}
			do {
				nextId = new Random().nextLong(maxId);
			}while(prevId == nextId);			
			idsArray.add(nextId);									
		}
		System.out.println(idsArray);		
		return questionRepo.findAllById(idsArray);
	}

}
