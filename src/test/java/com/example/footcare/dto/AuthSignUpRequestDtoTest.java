package com.example.footcare.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthSignUpRequestDtoTest {

    @Test
    @DisplayName("AuthSignUpRequestDto 필드값을 검증한다.")
    public void dtoTest() {
        // given
        String username = "김수박";
        String password = "abcdefg";
        String checkPassword = "abcdefg";
        String email = "kimsubak@naver.com";

        // when
        AuthSignUpRequestDto requestDto = new AuthSignUpRequestDto(username, password, checkPassword, email);

        // then
        assertThat(requestDto.getUsername()).isEqualTo(username);
        assertThat(requestDto.getPassword()).isEqualTo(password);
        assertThat(requestDto.getCheckPassword()).isEqualTo(checkPassword);
        assertThat(requestDto.getEmail()).isEqualTo(email);
    }
}