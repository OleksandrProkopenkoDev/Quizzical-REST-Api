package com.spro.repository;

import com.spro.security.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AppUserRepositoryTest {

    Logger logger = LoggerFactory.getLogger(AppUserRepositoryTest.class);

    @Autowired
    private AppUserRepository underTest;



    @Test
    void itShouldFindByUsername() {
        // Given
        AppUser user = new AppUser("testUsername", "testNickname", "testPassword");
        String username = user.getUsername();
        try {

            underTest.save(user);
        } catch (Exception e) {
            logger.error( String.format("userName [%s] already exists", username));
        }
        // When
        Optional<AppUser> optionalAppUser = underTest.findByUsername(username);
        // Then
        assertThat(optionalAppUser)
                .isPresent()
                .hasValueSatisfying(
                        u->assertThat(u)

                                .usingRecursiveComparison()
                                .ignoringFields("id")
                                .isEqualTo(user));
        logger.info("found user by username = "+username +" : "+optionalAppUser.get());
    }

    @Test
    void itShouldThrowWhenSaveExistingUsername() {
        // Given
        AppUser user1 = new AppUser("testUsername", "testNickname", "testPassword");
        AppUser user2 = new AppUser("testUsername", "testNickname2", "testPassword2");
        String username = user1.getUsername();
        AppUser savedUser1 = null;
        AppUser savedUser2 = null;
        try {
            savedUser1 = underTest.save(user1);
            savedUser2 = underTest.save(user2);

        } catch (Exception e) {
            logger.error( String.format("userName [%s] already exists", username));
        }
        logger.info("saved user 1 : "+savedUser1);
        logger.info("saved user 2 : "+savedUser2);
        // When
        assertThatThrownBy(()->underTest.save(user2))
                .isInstanceOfAny(Exception.class);
        // Then
    }

    @Test
    void itShouldFindNicknameById() {
        // Given
        AppUser user = new AppUser("testUsername", "testNickname", "testPassword");
        String nickname = user.getNickname();
        AppUser savedUser = null;
        try {
            savedUser = underTest.save(user);
        } catch (Exception e) {
            logger.error( String.format("userName [%s] already exists", user.getUsername()));
        }
        if(savedUser == null){
            savedUser = underTest.findByUsername(user.getUsername()).orElseThrow();
        }
        // When
        Optional<String> nicknameById = underTest.findNicknameById(savedUser.getId());
        // Then
        assertThat(nicknameById)
                .isPresent()
                .hasValue(user.getNickname());
        logger.info(String.format(
                "found nickname by id=[%s] is [%s]",
                savedUser.getId(),
                nicknameById.get()
        ));
    }
}