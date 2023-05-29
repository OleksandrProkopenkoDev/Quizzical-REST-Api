package com.spro.service;

import com.spro.dto.QuestionDto;
import com.spro.dto.QuestionDtoRequestMapper;
import com.spro.dto.QuestionDtoResponseMapper;
import com.spro.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    private QuestionService underTest;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionDtoRequestMapper questionDtoRequestMapper;
    @Mock
    private QuestionDtoResponseMapper questionDtoResponseMapper;

    @BeforeEach
    void setUp() {
        underTest = new QuestionService(
                questionRepository,
                questionDtoRequestMapper,
                questionDtoResponseMapper
        );
    }

    @Test
    void itShouldGetAvailableCategories() {
        // Given
    //need input list of questions dto:
        List<QuestionDto> questionsDto = List.of(
                new QuestionDto(
                        null,
                        "Entertainment: Video Games",
                        "multiple",
                        "easy",
                        "What&#039;s the name of the main protagonist in the &quot;Legend of Zelda&quot; franchise?",
                        "Link",
                        new String[]{"Mario","Zelda","Pit"}
                ),
                new QuestionDto(
                        null,
                        "Entertainment: Television",
                        "multiple",
                        "medium",
                        "What year did the television company BBC officially launch the channel BBC One?",
                        "1936",
                        new String[]{"1948","1932","1955"}
                ),
                new QuestionDto(
                        null,
                        "Entertainment: Board Games",
                        "multiple",
                        "easy",
                        "How many dice are used in the game of Yahtzee?",
                        "Five",
                        new String[]{"Four","Six","Eight"}
                )
        );
        // When
        underTest.saveListOfQuestions(questionsDto);
        // Then
        then(questionRepository).should().saveAll(anyList());
    }
}