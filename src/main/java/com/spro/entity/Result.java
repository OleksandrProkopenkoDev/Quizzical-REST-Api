package com.spro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="results")
public class Result {

	@Id
    @SequenceGenerator(
            name = "result_id_seq",
            sequenceName = "result_id_seq",
            allocationSize = 1
    )
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "result_id_seq")
	private Long id;
	private Integer numberOfQuestions;
	private Integer numberOfCorrectAnswers;
	private Integer elapsedTimeSeconds;//in seconds
	private String difficulty;
	private String category;
	private Long userId;

	public Result(
			Integer numberOfQuestions,
			Integer numberOfCorrectAnswers,
			Integer elapsedTimeSeconds,
			String difficulty,
			String category,
			Long userId) {
		this.numberOfQuestions = numberOfQuestions;
		this.numberOfCorrectAnswers = numberOfCorrectAnswers;
		this.elapsedTimeSeconds = elapsedTimeSeconds;
		this.difficulty = difficulty;
		this.category = category;
		this.userId = userId;
	}
	
	
}
