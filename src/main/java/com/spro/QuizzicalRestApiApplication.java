package com.spro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spro.entity.Question;
import com.spro.repository.QuestionRepository;

@SpringBootApplication
public class QuizzicalRestApiApplication implements CommandLineRunner {

	@Autowired
	private QuestionRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(QuizzicalRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//
//		String[] incorrectAnswers = {"M`ngke","`gedei","Tem`r"} ;
//		Question question = new Question(
//				"History",
//				"multiple",
//				"medium",
//				"What was Genghis Khan`s real name?",
//				"Tem`jin",
//				incorrectAnswers
//				);
//		System.out.println(question);
//		repo.save(question);
	}

}
