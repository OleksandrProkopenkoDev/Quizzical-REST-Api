package com.spro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.spro.dto.QuestionDto;
import com.spro.dto.QuestionDtoRequestMapper;
import com.spro.dto.QuestionDtoResponseMapper;
import org.springframework.stereotype.Service;

import com.spro.entity.Question;
import com.spro.repository.QuestionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	private final QuestionDtoRequestMapper questionDtoRequestMapper;
	private final QuestionDtoResponseMapper questionDtoResponseMapper;

	public List<QuestionDto> saveListOfQuestions(List<QuestionDto> questionsDto) {
		List<Question> questions = questionsDto.stream()
				.map(questionDtoRequestMapper)
				.collect(Collectors.toList());
		return questionRepository.saveAll(questions)
				.stream()
				.map(questionDtoResponseMapper)
				.collect(Collectors.toList());
	}

	public List<QuestionDto> findRandomQuestionsBy(Map<String, String> requestParams) {

		Integer amount = requestParams.get("amount") == null ? null : Integer.valueOf( requestParams.get("amount") );
		String category = requestParams.get("category");
		String difficulty = requestParams.get("difficulty");

		amount = validateAmount(amount);

		if(category == null && difficulty == null){
			return findRandomQuestionsByAmount(amount);
		} else if(difficulty == null){
			return findRandomQuestionsByCategory(amount, category);
		}else if(category == null){
			return findRandomQuestionsByDifficulty(amount, difficulty);
		}else{
			return findRandomQuestionsByAmountDifficultyAndCategory(amount,difficulty,category);
		}

	}

	private List<QuestionDto> findRandomQuestionsByAmountDifficultyAndCategory(
			Integer amount,
			String difficulty, 
			String category) {

		List<Question>
			questions = questionRepository.findAllByDifficultyAndCategory(difficulty, category);

		return selectAmountOfRandomQuestionsFromList(amount, questions);
	}

	private List<QuestionDto> findRandomQuestionsByDifficulty(Integer amount, String difficulty) {
		List<Question>
			questions = questionRepository.findAllByDifficulty(difficulty);

		return selectAmountOfRandomQuestionsFromList(amount, questions);
	}

	private List<QuestionDto> findRandomQuestionsByCategory(Integer amount, String category) {

		List<Question> questions = questionRepository.findAllByCategory(category);

		return selectAmountOfRandomQuestionsFromList(amount, questions);
	}


	private List<QuestionDto> findRandomQuestionsByAmount(Integer amount) {

		List<Question> questions = questionRepository.findAll();

		return selectAmountOfRandomQuestionsFromList(amount, questions);
	}

	private List<QuestionDto> selectAmountOfRandomQuestionsFromList(Integer amount, List<Question> questions){
		//generate random IDs array in range [0, questions.size()]
		List<Integer> idsArray = generateRandomIdArray(amount, questions.size());

		//find questions using generated IDs array
		List<Question> questionList = idsArray.stream()
				.map(questions::get)
				.toList();

		return questionList.stream().map(questionDtoResponseMapper).collect(Collectors.toList());
	}

	private static Integer validateAmount(Integer amount) {
		if(amount == null) amount = 5;
		if(amount < 1) amount = 1;
		if (amount > 10) amount = 10;
		return amount;
	}

	private static List<Integer> generateRandomIdArray(int amount, long maxId) {
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
		
		return questionRepository.findAllCategoriesDistinct();
	}

}
