package com.example.footcare.Repository;

import com.example.footcare.Model.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AuthRepositoryTest {

    @Autowired
    AuthRepository authRepository;

    @Nested
    @DisplayName("USERS 테이블은 ")
    class userTable {
        @Test
        @Transactional
        @DisplayName("user 한 명을 INSERT 한다.")
        void insertOneUser() {
            // given
            Users user = new Users("김수박", "abcdefg");

            authRepository.save(user);

            // when
            List<Users> usersList = authRepository.findAll();

            // then
            assertThat(usersList).hasSize(1)
                    .extracting("id", "name", "password")
                    .containsExactlyInAnyOrder(
                            tuple(1L, "김수박", "abcdefg")
                    );
        }

        @Test
        @DisplayName("user 세 명을 INSERT 한다.")
        void insertThreeUsers() {
            // given
            Users user1 = new Users("김수박", "abcdefg");
            Users user2 = new Users("박참외", "hijklmn");
            Users user3 = new Users("이사과", "opqrstu");

            authRepository.saveAll(List.of(user1, user2, user3));

            // when
            List<Users> usersList = authRepository.findAll();

            // then
            assertThat(usersList).hasSize(3)
                    .extracting("id", "name", "password")
                    .containsExactlyInAnyOrder(
                            tuple(2L, "김수박", "abcdefg"),
                            tuple(3L, "박참외", "hijklmn"),
                            tuple(4L, "이사과", "opqrstu")
                    );
        }

    }
}