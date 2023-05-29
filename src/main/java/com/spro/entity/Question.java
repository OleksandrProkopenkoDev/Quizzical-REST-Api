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
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString

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
	@Column(name="correct_answer")
	private String correctAnswer;
	@Column(name="incorrect_answers")
	private String[] incorrectAnswers;

}


/*	@Override
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
	}*/