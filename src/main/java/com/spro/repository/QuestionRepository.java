package com.spro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spro.entity.Question;


public interface QuestionRepository extends JpaRepository<Question, Long>{

}
