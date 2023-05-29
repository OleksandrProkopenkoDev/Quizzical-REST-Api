package com.spro.dto;

import com.spro.entity.Question;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class QuestionDtoRequestMapper implements Function<QuestionDto, Question> {
    @Override
    public Question apply(QuestionDto questionDto) {
        return new Question(
                null,
                questionDto.category(),
                questionDto.type(),
                questionDto.difficulty(),
                questionDto.question(),
                questionDto.correct_answer(),
                questionDto.incorrect_answers()
        );
    }
}

