package com.spro.repository;

import com.spro.entity.Result;
import org.junit.jupiter.api.Test;
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
class ResultRepositoryTest {

    Logger logger = LoggerFactory.getLogger(ResultRepositoryTest.class);

    @Autowired
    private ResultRepository underTest;
    @Test
    void itShouldFindAllByUserId() {
        // Given
    Long userId = 1000L;
        Result result1 = new Result(
                5,
                3,
                23,
                "medium",
                "History",
                userId
        );
        underTest.save(result1);
        Result result2 = new Result(
                5,
                4,
                33,
                "easy",
                "Geography",
                userId
        );
        underTest.save(result2);
        // When
        List<Result> allByUserId = underTest.findAllByUserId(userId);
        // Then
        assertThat(allByUserId)
                .hasSize(2);
        assertThat(allByUserId.get(0))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(result1);
        assertThat(allByUserId.get(1))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(result2);
    }
}