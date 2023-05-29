package com.spro.repository;

import com.spro.entity.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class QuestionRepositoryTest {

    Logger logger = LoggerFactory.getLogger(QuestionRepositoryTest.class);

    @Autowired
    private QuestionRepository underTest;

    @ParameterizedTest
    @CsvSource({
            "History, medium",
            "Geography, easy",
            "Entertainment: Video Games, hard"
    })
    void itShouldFindAllByDifficultyAndCategory(String category, String difficulty) {
        // Given

        // When
        List<Question> questionList =
                underTest.findAllByDifficultyAndCategory(difficulty, category);
        // Then
        assertThat(questionList).isNotEmpty();
        logger.info("questionList : "+questionList);
    }

    @ParameterizedTest
    @CsvSource({
            ", medium",
            "Geography, ",
            " , "
    })
    void itShouldFindNoneWhenDifficultyOrCategoryIsNull(String category, String difficulty) {
        // Given

        // When
        List<Question> questionList =
                underTest.findAllByDifficultyAndCategory(difficulty, category);
        // Then
        assertThat(questionList).isEmpty();
        logger.info("questionList : "+questionList);
    }

    @ParameterizedTest
    @CsvSource({
            "medium",
            "easy",
            "hard"
    })
    void itShouldFindAllByDifficulty(String difficulty) {
        // Given
        // When
        List<Question> questionList =
                underTest.findAllByDifficulty(difficulty);
        // Then
        assertThat(questionList).isNotEmpty();
        logger.info("questionList : "+questionList);
    }

    @Test
    void itShouldFindNoneWhenDifficultyIsNull() {
        // Given
        String difficulty = null;
        // When
        List<Question> questionList =
                underTest.findAllByDifficulty(difficulty);
        // Then
        assertThat(questionList).isEmpty();
        logger.info("questionList : "+questionList);
    }

    @ParameterizedTest
    @CsvSource({
            "History",
            "Geography",
            "Entertainment: Video Games"
    })
    void itShouldFindAllByCategory(String category) {
        // Given
        // When
        List<Question> questionList =
                underTest.findAllByCategory(category);
        // Then
        assertThat(questionList).isNotEmpty();
        logger.info("questionList : "+questionList);
    }
    @Test
    void itShouldFindNoneWhenCategoryIsNull() {
        // Given
        String category = null;
        // When
        List<Question> questionList =
                underTest.findAllByCategory(category);
        // Then
        assertThat(questionList).isEmpty();
        logger.info("questionList : "+questionList);
    }

    @Test
    void itShouldFindAllCategoriesDistinct() {
        // Given

        // When
        List<String> categoriesDistinct = underTest.findAllCategoriesDistinct();

        // Then
        assertThat(categoriesDistinct)
                .isNotEmpty()
                .doesNotHaveDuplicates();
        logger.info("categoriesDistinct : "+categoriesDistinct);
    }
}