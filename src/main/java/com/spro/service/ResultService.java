package com.spro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spro.dto.ResponseRatingRecord;
import com.spro.dto.UserStatistics;
import com.spro.entity.RatingRecord;
import com.spro.entity.Result;
import com.spro.repository.AppUserRepository;
import com.spro.repository.RatingRecordRepository;
import com.spro.repository.ResultRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ResultService {

	private final ResultRepository resultRepository;
	private final RatingRecordRepository ratingRecordRepository;
	private final AppUserRepository appUserRepository;

	public Result saveResult(Result result) {
		Result response = resultRepository.save(result);
		//need recalculate and save rating for this player
		//1a get prev ratingrecord for this user
		RatingRecord ratingRecord = ratingRecordRepository.findByUserId(result.getUserId())
									.orElse(new RatingRecord(result.getUserId(), 0));
		//2. calculate rating
//		long ratingLong = calculateRating(result.getUserId());
		List<Result> userResults = resultRepository.findAllByUserId(result.getUserId());
		long ratingLong = new UserStatistics(userResults).getRating();
		//3.save rating to theDB
		ratingRecord.setRating((int)ratingLong);
		ratingRecordRepository.save(ratingRecord);
		
		 return response;
	}


	public List<Result> getUserResults(Long userId) {
		return resultRepository.findAllByUserId(userId);
	}
	
	public UserStatistics getUserStatistics(Long userId) {
		List<Result> userResults = resultRepository.findAllByUserId(userId);
		return new UserStatistics(userResults);
	}
	
	public List<ResponseRatingRecord> ratingList(){
				
		//get all rating records
		List<RatingRecord> ratingList = 
				ratingRecordRepository.findAll(
						Sort.by("rating").descending());//sort users by rating
		//create response object
		//object is a list
		List<ResponseRatingRecord> responseList =
			ratingList.stream()
			.map( 
					(ratingRecord) -> 
					new ResponseRatingRecord(
							ratingList.indexOf(ratingRecord)+1, 
							appUserRepository.findNicknameById(ratingRecord.getUserId()).orElse("no nickname found"), 
							ratingRecord.getRating())
					
					)
			.collect(Collectors.toList());
		
		//object contains position, nickname, rating
		//elements in list are sortrd by position from 1 to max
		
		 
		return responseList;
		
		
		
		
		
		
	}
	
}
