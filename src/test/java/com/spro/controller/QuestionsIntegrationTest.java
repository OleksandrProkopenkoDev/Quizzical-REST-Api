package com.spro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spro.dto.QuestionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldSaveListOfQuestions() throws Exception {
        // Given a list of question dtos
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

        // When  questions POST request is sent
        //mock mvc to perform api call
        ResultActions postQuestionsResultActions = mockMvc.perform(post("/api/v1/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(questionsDto))
        );


        // Then

        postQuestionsResultActions
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @ParameterizedTest
    @CsvSource({
            "4, History, medium",
            "-2, Geography, medium",
            "12, Entertainment: Video Games, ",
            " , , ",
            " , Geography, ",
            ", , medium",
            "2, , "
    })
    void itShouldGetQuestionsList(Integer amount, String category, String difficulty) throws Exception {
        // Given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("amount", amount == null ? null : amount.toString());
        params.add("category", category);
        params.add("difficulty", difficulty);
        // When  questions POST request is sent
        //mock mvc to perform api call
        ResultActions getQuestionResultActions =
                mockMvc.perform(get("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .params(params)
        );

        // Then
        getQuestionResultActions
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));

    }

    @Test
    void itShouldGetCategoriesList() throws Exception {
        // Given


        //mock mvc to perform api call
        ResultActions getCategoriesResultActions =
                mockMvc.perform(get("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                );

        // Then
        getCategoriesResultActions
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));

    }

    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return "";
        }

    }
}
