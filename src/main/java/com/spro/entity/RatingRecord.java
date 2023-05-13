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

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="rating_records")
public class RatingRecord {

	@Id
    @SequenceGenerator(
            name = "result_id_seq",
            sequenceName = "result_id_seq",
            allocationSize = 1
    )
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Long userId;
	private Integer rating;

	public RatingRecord(Long userId, Integer rating) {
		this.userId = userId;
		this.rating = rating;
	}

	
	
	
}
