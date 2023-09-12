package com.example.footcare.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoginInfoDtoTest {

    @Test
    @DisplayName("LoginInfoDto 필드값을 검증한다.")
    public void dtoTest() {
        // given
        String username = "김수박";
        String password = "abcdefg";

        // when
        LoginInfoDto requestDto = new LoginInfoDto(username, password);

        // then
        assertThat(requestDto.getUsername()).isEqualTo(username);
        assertThat(requestDto.getPassword()).isEqualTo(password);
    }

}