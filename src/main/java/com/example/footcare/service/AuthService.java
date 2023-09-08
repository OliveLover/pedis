package com.example.footcare.service;

import com.example.footcare.dto.AuthLoginRequestDto;
import com.example.footcare.dto.AuthResponseDto;
import com.example.footcare.dto.AuthSignUpRequestDto;
import com.example.footcare.model.Users;
import com.example.footcare.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<AuthResponseDto> createUser(AuthSignUpRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        isPasswordMatch(requestDto);

        Users users = new Users(username, password, email);
        AuthResponseDto responseDto = new AuthResponseDto("생성 완료");

        authRepository.save(users);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    public ResponseEntity<AuthResponseDto> login(AuthLoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<Users> findUser = authRepository.findByUsername(username);

        if (!passwordEncoder.matches(password, findUser.get().getPassword())) {
            throw new BadCredentialsException("비밀번호를 다시 확인해 주세요.");
        }

        AuthResponseDto responseDto = new AuthResponseDto("로그인 완료");

        System.out.println(findUser.get().getPassword());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    private static void isPasswordMatch(AuthSignUpRequestDto requestDto) {
        if (!requestDto.getPassword().equals(requestDto.getCheckPassword())) {
            throw new IllegalArgumentException("두 개의 비밀번호가 일치하지 않습니다.");
        }
    }
}
