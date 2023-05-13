package com.spro.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spro.entity.RatingRecord;
import com.spro.entity.Result;

import lombok.AllArgsConstructor;


@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	private  RatingRecordRepository ratingRecordRepository;
	@Autowired
	private ResultRepository resultRepository;

	@Test
	public void countRating() {
		List<Result> userResults = resultRepository.findAllByUserId(3L);
		userResults.forEach(System.out::println);
		long correctAnswersEasy = 
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("easy"))
				.collect(Collectors.summingInt(Result::getNumberOfCorrectAnswers));
		long correctAnswersMedium = 
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("medium"))
				.collect(Collectors.summingInt(Result::getNumberOfCorrectAnswers));
		long correctAnswersHard = 
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("hard"))
				.collect(Collectors.summingInt(Result::getNumberOfCorrectAnswers));
		//total questions
		long totalQuestionsEasy = 
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("easy"))
				.collect(Collectors.summingInt(Result::getNumberOfQuestions));
		long totalQuestionsMedium = 
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("medium"))
				.collect(Collectors.summingInt(Result::getNumberOfQuestions));
		long totalQuestionsHard = 
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("hard"))
				.collect(Collectors.summingInt(Result::getNumberOfQuestions));
		long totalQuestions = totalQuestionsEasy+totalQuestionsMedium+totalQuestionsHard;
		System.out.println("total questions = "+totalQuestions);
		double k = totalQuestions > 100 ? 1 : (double)totalQuestions/100;
		System.out.println("k= "+k);
		Double rating = (k*(  (double) (correctAnswersEasy/ (double)totalQuestionsEasy)*50 
								+ (double) (correctAnswersMedium/(double)totalQuestionsMedium)*30
								+ (double) (correctAnswersHard/(double)totalQuestionsHard)*20 ) );
		long ratingLong = Math.round(rating);
		System.out.println("rating double = "+rating);
		System.out.println("rating long = "+ratingLong);
	}
	
//	@Test
	public void getResultsByUserId() {
		Long userId = 3L;
		List<Result> usersResults = resultRepository.findAllByUserId(userId);
//		System.out.println(findAllByUserId);
		usersResults.forEach(System.out::println);
	}
	
//	@Test
	public void saveRecord() {
		RatingRecord record = new RatingRecord(1L, 78);
		ratingRecordRepository.save(record);
	}
}
