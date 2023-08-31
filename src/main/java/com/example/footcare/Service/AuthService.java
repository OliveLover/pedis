package com.example.footcare.Service;

import com.example.footcare.Dto.UserRequestDto;
import com.example.footcare.Dto.UserResponseDto;
import com.example.footcare.Model.Users;
import com.example.footcare.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthRepository authRepository;

    @Transactional
    public ResponseEntity<UserResponseDto> createUser(UserRequestDto requestDto) {
        String name = requestDto.getUsername();
        String password = requestDto.getPassword();

        Users users = new Users(name, password);
        UserResponseDto responseDto = new UserResponseDto("생성 완료");

        authRepository.save(users);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
