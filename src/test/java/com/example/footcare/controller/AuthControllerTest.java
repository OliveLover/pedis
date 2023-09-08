package com.example.footcare.controller;

import com.example.footcare.dto.AuthLoginRequestDto;
import com.example.footcare.dto.AuthSignUpRequestDto;
import com.example.footcare.model.Users;
import com.example.footcare.repository.AuthRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuthRepository authRepository;

    @Nested
    @DisplayName("\"/api/v1/join\" 를 호출할 때, ")
    @Transactional
    class createUser {
        @Test
        @DisplayName("AuthSignUpRequestDto의 인자값이 모두 존재하면, \"CREATED\"와 JSON 형식으로 \"msg\"가 출력된다.")
        void testCreateUserWithAllFieldsReturnsCreatedStatusAndMessageInJsonFormat() throws Exception {
            // given
            AuthSignUpRequestDto requestDto = new AuthSignUpRequestDto("김수박", "abcdefg", "abcdefg", "kimsubak@naver.com");

            // when & then
            mockMvc.perform(post("/api/v1/join")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestDto))
                            .with(csrf()))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.msg", is("생성 완료")).exists())
                    .andDo(print());
        }

        @Test
        @DisplayName("AuthSignUpRequestDto의 인자값 중 username을 입력하지 않으면, \"BAD_REQUEST\"와 String 형식으로 \"사용자 ID는 필수항목입니다.\"가 출력된다.")
        void createUserWithoutUsernameReturnsErrorMessage() throws Exception {
            // given
            AuthSignUpRequestDto requestDto = new AuthSignUpRequestDto("", "abcdefg", "abcdefg", "kimsubak@naver.com");

            // when & then
            mockMvc.perform(post("/api/v1/join")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestDto))
                            .with(csrf()))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("사용자 ID는 필수항목입니다."))
                    .andDo(print());
        }

        @Test
        @DisplayName("AuthSignUpRequestDto의 인자값 중 password를 입력하지 않으면, \"BAD_REQUEST\"와 String 형식으로 \"비밀번호는 필수항목입니다.\"가 출력된다.")
        void createUserWithoutPasswordReturnsErrorMessage() throws Exception {
            // given
            AuthSignUpRequestDto requestDto = new AuthSignUpRequestDto("김수박", "", "abcdefg", "kimsubak@naver.com");

            // when & then
            mockMvc.perform(post("/api/v1/join")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestDto))
                            .with(csrf()))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("비밀번호는 필수항목입니다."))
                    .andDo(print());
        }

        @Test
        @DisplayName("AuthSignUpRequestDto의 인자값 중 checkPassword를 입력하지 않으면, \"BAD_REQUEST\"와 String 형식으로 \"비밀번호 확인이 필요합니다.\"가 출력된다.")
        void createUserWithoutCheckPasswordReturnsErrorMessage() throws Exception {
            // given
            AuthSignUpRequestDto requestDto = new AuthSignUpRequestDto("김수박", "abcdefg", "", "kimsubak@naver.com");

            // when & then
            mockMvc.perform(post("/api/v1/join")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestDto))
                            .with(csrf()))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("비밀번호 확인이 필요합니다."))
                    .andDo(print());
        }

        @Test
        @DisplayName("AuthSignUpRequestDto의 인자값 중 두개의 password가 일치하지 않으면, \"BAD_REQUEST\"와 String 형식으로 \"두 개의 비밀번호가 일치하지 않습니다.\"가 출력된다.")
        void passwordsNotMatching() throws Exception {
            // given
            AuthSignUpRequestDto requestDto = new AuthSignUpRequestDto("김수박", "abcdefg", "abcd", "kimsubak@naver.com");

            // when & then
            mockMvc.perform(post("/api/v1/join")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestDto))
                            .with(csrf()))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("두 개의 비밀번호가 일치하지 않습니다."))
                    .andDo(print());
        }

        @Test
        @DisplayName("AuthSignUpRequestDto의 인자값 중 password를 입력하지 않으면, \"BAD_REQUEST\"와 String 형식으로 \"이메일은 필수항목입니다.\"가 출력된다.")
        void createUserWithoutEmailReturnsErrorMessage() throws Exception {
            // given
            AuthSignUpRequestDto requestDto = new AuthSignUpRequestDto("김수박", "abcdefg", "abcdefg", "");

            // when & then
            mockMvc.perform(post("/api/v1/join")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestDto))
                            .with(csrf()))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("이메일은 필수항목입니다."))
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("\"/api/v1/login\" 를 호출할 때, ")
    @Transactional
    class login {

        @BeforeEach
        void setUp() {
            String hashedPassword = new BCryptPasswordEncoder().encode("abcdefg");
            Users user = new Users("김수박", hashedPassword, "kimsubak@naver.com");
            authRepository.save(user);
        }

        @Test
        @DisplayName("AuthLoginRequestDto의 인자값이 모두 존재하면, \"OK\"와 JSON 형식으로 \"msg\"가 출력된다.")
        void testLoginWithAllFieldsReturnsCreatedStatusAndMessageInJsonFormat() throws Exception {
            // given
            AuthLoginRequestDto requestDto = new AuthLoginRequestDto("김수박", "abcdefg");

            // when & then
            mockMvc.perform(post("/api/v1/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestDto))
                            .with(csrf()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.msg", is("로그인 완료")).exists())
                    .andDo(print());
        }

        @Test
        @DisplayName("AuthLoginRequestDto의 password가 틀릴 경우, \"OK\"와 JSON 형식으로 \"msg\"가 출력된다.")
        void testLoginWithInvalidPasswordReturnsErrorMessageInJsonFormat() throws Exception {
            // given
            AuthLoginRequestDto requestDto = new AuthLoginRequestDto("김수박", "aaa");

            // when & then
            mockMvc.perform(post("/api/v1/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(requestDto))
                            .with(csrf()))
                    .andExpect(status().isForbidden())
                    .andExpect(content().string("비밀번호를 다시 확인해 주세요."))
                    .andDo(print());
        }

    }
}