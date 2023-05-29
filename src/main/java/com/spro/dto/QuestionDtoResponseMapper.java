package com.spro.dto;

import com.spro.entity.Question;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class QuestionDtoResponseMapper implements Function<Question, QuestionDto> {
    @Override
    public QuestionDto apply(Question question) {
        return new QuestionDto(
                question.getId(),
                question.getCategory(),
                question.getType(),
                question.getDifficulty(),
                question.getQuestion(),
                question.getCorrectAnswer(),
                question.getIncorrectAnswers()
        );
    }
}
