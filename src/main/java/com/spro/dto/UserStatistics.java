package com.spro.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.spro.entity.Result;

import lombok.Getter;
import lombok.ToString;


@ToString

public class UserStatistics {
	//this is input data
	private long correctAnswersEasy = 0;
	private long correctAnswersMedium = 0;
	private long correctAnswersHard = 0;
	private long totalQuestionsEasy=1;
	private long totalQuestionsMedium=1;
	private long totalQuestionsHard=1;
	private long timeElapsedEasy=0;
	private long timeElapsedMedium=0;
	private long timeElapsedHard=0;
	private long correctAnswersTotal=0;
	private long totalQuestions=1;
	private long timeElapsedTotal=0;
	
	
	//this we must calculate during construction
	private long timeElapsedPerQuestionEasy;
	private long timeElapsedPerQuestionMedium;
	private long timeElapsedPerQuestionHard;
	private long timeElapsedPerQuestionTotal;
	private long timeElapsedPerCorrectAnswerEasy;
	private long timeElapsedPerCorrectAnswerMedium;
	private long timeElapsedPerCorrectAnswerHard;
	private long timeElapsedPerCorrectAnswerTotal;
	private double k;//coeficient that means totalQeustion/100 . Its needed to balance rating for new players (before 100 questions answered)
	private long rating;
	
	public UserStatistics(List<Result> userResults) {
		
		correctAnswersEasy =
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("easy"))
				.collect(Collectors.summingInt(Result::getNumberOfCorrectAnswers));
//		System.out.println("correct answers easy = "+correctAnswersEasy);
		
		correctAnswersMedium =
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("medium") ||res.getDifficulty().equalsIgnoreCase("any"))
				.collect(Collectors.summingInt(Result::getNumberOfCorrectAnswers));
//		System.out.println("correct answers medium = "+correctAnswersMedium);
		
		correctAnswersHard =
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("hard"))
				.collect(Collectors.summingInt(Result::getNumberOfCorrectAnswers));
//		System.out.println("correct answers hard = "+correctAnswersHard);
		//total questions
		correctAnswersTotal =
				userResults.stream()
				.collect(Collectors.summingInt(Result::getNumberOfCorrectAnswers));
		
		
		totalQuestionsEasy = 
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("easy"))
				.collect(Collectors.summingInt(Result::getNumberOfQuestions));
		if(totalQuestionsEasy == 0) totalQuestionsEasy = 1;
//		System.out.println("total questions easy = "+totalQuestionsEasy);
		
		totalQuestionsMedium =
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("medium")||res.getDifficulty().equalsIgnoreCase("any") )
				.collect(Collectors.summingInt(Result::getNumberOfQuestions));
		if(totalQuestionsMedium == 0) totalQuestionsMedium = 1;
//		System.out.println("total questions medium = "+totalQuestionsMedium);
		
		totalQuestionsHard =
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("hard"))
				.collect(Collectors.summingInt(Result::getNumberOfQuestions));
		if(totalQuestionsHard == 0) totalQuestionsHard = 1;
		
//		totalQuestions = totalQuestionsEasy+totalQuestionsMedium+totalQuestionsHard;

		totalQuestions =
				userResults.stream()
				
				.collect(Collectors.summingInt(Result::getNumberOfQuestions));
		
		//		System.out.println("total questions hard = "+totalQuestionsHard);
		k = totalQuestions>100? 1 : (double)totalQuestions/100;
		
		//rating with 3 difficulty levels  (apply this or next)
		double ratingWithDifficulty = (k*(  (double) (correctAnswersEasy/ (double)totalQuestionsEasy)*50 
				+ (double) (correctAnswersMedium/(double)totalQuestionsMedium)*30
				+ (double) (correctAnswersHard/(double)totalQuestionsHard)*20 ) );
		//rating without difficulty
		double ratingAny = (k*(  (double) correctAnswersTotal/ (double)totalQuestions)*100 ) ;
//		System.out.println("k="+k);
		rating = Math.round(ratingAny);
//		System.out.println("rating="+rating);
		
		//--------------------time elapsed---------------
		timeElapsedEasy =
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("easy"))
				.collect(Collectors.summingInt(Result::getElapsedTimeSeconds));
		
		timeElapsedMedium =
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("medium"))
				.collect(Collectors.summingInt(Result::getElapsedTimeSeconds));
		
		timeElapsedHard =
				userResults.stream()
				.filter(res->res.getDifficulty().equalsIgnoreCase("hard"))
				.collect(Collectors.summingInt(Result::getElapsedTimeSeconds));
		timeElapsedTotal = 
				userResults.stream()
				.collect(Collectors.summingInt(Result::getElapsedTimeSeconds));
		
		
//		calculate time--------
		timeElapsedPerQuestionEasy = Math.round( (double)timeElapsedEasy/ (double)totalQuestionsEasy );
		timeElapsedPerQuestionMedium = Math.round( (double)timeElapsedMedium/ (double)totalQuestionsMedium );
		timeElapsedPerQuestionHard = Math.round( (double)timeElapsedHard/ (double)totalQuestionsHard );
		timeElapsedPerQuestionTotal = Math.round( (double)timeElapsedTotal/ (double)totalQuestions );
	
		timeElapsedPerCorrectAnswerEasy = Math.round( (double)timeElapsedEasy/ (double)correctAnswersEasy );
		timeElapsedPerCorrectAnswerMedium = Math.round( (double)timeElapsedMedium/ (double)correctAnswersMedium );
		timeElapsedPerCorrectAnswerHard = Math.round( (double)timeElapsedHard/ (double)correctAnswersHard );
		timeElapsedPerCorrectAnswerTotal = Math.round( (double)timeElapsedTotal/ (double)correctAnswersTotal );
	
	
	}
	
	public Long getRating() {
		return rating;
	}
	
	public List<StatisticElement> getTotal(){
		List<StatisticElement> output = new ArrayList<>();
		output.add(new StatisticElement("Correct answers", (int)correctAnswersTotal));
		output.add(new StatisticElement("Questions", (int)totalQuestions));
		output.add(new StatisticElement("Time elapsed", (int)timeElapsedTotal));
		output.add(new StatisticElement("Time per question", (int)timeElapsedPerQuestionTotal));
		output.add(new StatisticElement("Time per correct answer", (int)timeElapsedPerCorrectAnswerTotal));
		
		return output;
	}
	
}
