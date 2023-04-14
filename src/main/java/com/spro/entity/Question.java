package com.spro.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long id;

	private String category;
	private String type;
	private String difficulty;
	private String question;
	private String correct_answer;

	private String[] incorrect_answers;
	
//	@OneToMany(
//			cascade = CascadeType.ALL, 
//			fetch = FetchType.EAGER)
//	@JoinColumn(name = "question_id")
//	private List<IncorrectAnswer> incorrect_answers;

	public Question() {
	}

	public Question(String category, String type, String difficulty, String question, String correctAnswer,
			String[] incorrect_answers) {
		this.category = category;
		this.type = type;
		this.difficulty = difficulty;
		this.question = question;
		this.correct_answer = correctAnswer;
		this.incorrect_answers = incorrect_answers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correct_answer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correct_answer = correctAnswer;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	public String[] getIncorrect_answers() {
		return incorrect_answers;
	}

	public void setIncorrect_answers(String[] incorrect_answers) {
		this.incorrect_answers = incorrect_answers;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", category=" + category + ", type=" + type + ", difficulty=" + difficulty
				+ ", question=" + question + ", correct_answer=" + correct_answer + ", incorrect_answers="
				+ incorrectAnswersToString() + "]";
	}

	
	private String incorrectAnswersToString() {
		StringBuilder result = new StringBuilder("[");
		
		for (int i = 0; i < incorrect_answers.length; i++) {
			result.append(incorrect_answers[i]).append(", ");
		}
		
		result.append("]");
		return result.toString();
	}
}
