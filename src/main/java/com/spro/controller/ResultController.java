package com.spro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spro.dto.ResponseRatingRecord;
import com.spro.dto.UserStatistics;
import com.spro.entity.RatingRecord;
import com.spro.entity.Result;
import com.spro.service.ResultService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*","http://localhost:3000"})
public class ResultController {

	private final ResultService resultService;
	
	@PostMapping("results")
	public ResponseEntity<Result> saveQuizResults(@RequestBody Result result) {
		return ResponseEntity.ok(resultService.saveResult(result)) ;
		
	}
	
	@GetMapping("results/{userId}")
	public UserStatistics getUserResults(
			@PathVariable("userId")Long userId){
		
		return resultService.getUserStatistics(userId);
	}
	
	@GetMapping("ratingtable")
	public List<ResponseRatingRecord> getRatingTable(){
		return resultService.ratingList();
	}
}
