package com.example.footcare.service;

import com.example.footcare.dto.UserRequestDto;
import com.example.footcare.dto.UserResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    @DisplayName("requestDto의 인자값을 받아 계정을 생성하면 \"HTTPStatus.CREATED\"와 \"생성 완료\"가 출력된다.")
    void createUser() {
        // given
        UserRequestDto requestDto = new UserRequestDto("김수박", "abcdefg", "kimsubak@naver.com");

        // when
        ResponseEntity<UserResponseDto> response = authService.createUser(requestDto);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(response.getBody()).getMsg()).isEqualTo("생성 완료");
    }
}