package com.example.footcare.config.auth;

import com.example.footcare.data.DatabaseClearExtension;
import com.example.footcare.model.Users;
import com.example.footcare.repository.AuthRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @MockBean
    private AuthRepository authRepository;

    @Test
    @DisplayName("user 정보가 USERS 테이블에 존재할 때 UserDetailsImpl을 생성하면 검증을 통과한다.")
    void testLoadUserByUsernameUserExistsInDB() {
        // given
        String username = "김수박";
        Users mockUser = new Users(username, "abcdefg", "kimsubak@naver.com");
        when(authRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // when
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);

        // then
        assertThat(userDetailsImpl).isNotNull();
        assertThat(userDetailsImpl.getUsername()).isEqualTo(username);
        assertThat(userDetailsImpl.getPassword()).isEqualTo("abcdefg");
        assertThat(userDetailsImpl.getAuthorities()).isNull();
    }

    @Test
    @DisplayName("user 정보가 USERS 테이블에 존재하지 않을때 UserDetailsImpl을 생성하면 \"NOT_FOUD\"와 \"존재하지 않는 사용자입니다.\"가 출력 된다.")
    void testUserDetailsImplWhenUserDoesNotExistInDatabase() {
        // given
        String findUsername = "박참외";
        when(authRepository.findByUsername(findUsername)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> userDetailsServiceImpl.loadUserByUsername(findUsername))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("존재하지 않는 사용자입니다.");
    }
}