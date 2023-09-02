package com.example.footcare.service;

import com.example.footcare.dto.UserRequestDto;
import com.example.footcare.dto.UserResponseDto;
import com.example.footcare.model.Users;
import com.example.footcare.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<UserResponseDto> createUser(UserRequestDto requestDto) {
        String name = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        if (Objects.equals(name, "") || name.isEmpty()) {
            // name이 비어있을 때 예외를 던지거나 오류 메시지를 반환
            String errorMessage = "이름을 입력하세요.";
            return new ResponseEntity<>(new UserResponseDto(errorMessage), HttpStatus.BAD_REQUEST);
        }

        Users users = new Users(name, password, email);
        UserResponseDto responseDto = new UserResponseDto("생성 완료");

        authRepository.save(users);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
