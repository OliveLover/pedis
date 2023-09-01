package com.example.footcare.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserResponseDtoTest {

    @Test
    @DisplayName("UserResponseDto 필드값을 검증한다.")
    public void dtoTest() {
        // given
        String msg = "생성 완료";

        // when
        UserResponseDto responseDto = new UserResponseDto(msg);

        // then
        assertThat(responseDto.getMsg()).isEqualTo(msg);
    }
}