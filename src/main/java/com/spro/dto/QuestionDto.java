package com.spro.dto;

import jakarta.persistence.Column;

public record QuestionDto(
        Long id,
        String category,
        String type,
        String difficulty,
        String question,
        String correct_answer,
        String[] incorrect_answers
) {
}
