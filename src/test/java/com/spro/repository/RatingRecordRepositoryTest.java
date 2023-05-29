package com.spro.repository;

import com.spro.entity.RatingRecord;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class RatingRecordRepositoryTest {

    Logger logger = LoggerFactory.getLogger(RatingRecordRepositoryTest.class);
    @Autowired
    private RatingRecordRepository underTest;

    @Test
    void itShouldFindByUserId() {
        // Given
        List<RatingRecord> records = underTest.findAll();
        RatingRecord ratingRecord = records.get(0);
        // When
        Long userId = ratingRecord.getUserId();
        Optional<RatingRecord> recordOptional = underTest.findByUserId(userId);
        // Then
        assertThat(recordOptional)
                .isPresent();
        assertThat(recordOptional.get().getUserId())
                .isEqualTo(userId);
        logger.info(String.format(
                "ratingRecord found by userId=[%s] : %s",
                userId,
                recordOptional.get()
        ));
    }
}