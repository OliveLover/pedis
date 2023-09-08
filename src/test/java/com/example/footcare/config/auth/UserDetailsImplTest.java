package com.example.footcare.config.auth;

import com.example.footcare.model.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserDetailsImplTest {

    @Test
    @DisplayName("user의 이름과 user객체로 생성한 userDetailsImpl의 이름은 같다.")
    void testGetUsername() {
        // given
        String hashedPassword = new BCryptPasswordEncoder().encode("abcdefg");
        Users user = new Users("김수박", hashedPassword, "kimsubak@naver.com");

        // when
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);

        // then
        assertThat(user.getUsername()).isEqualTo(userDetailsImpl.getUsername());
        assertThat(user.getPassword()).isEqualTo(userDetailsImpl.getPassword());
    }

}