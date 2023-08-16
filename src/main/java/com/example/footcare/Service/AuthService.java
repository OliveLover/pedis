package com.example.footcare.Service;

import com.example.footcare.Dto.UserRequestDto;
import com.example.footcare.Model.Users;
import com.example.footcare.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private AuthRepository authRepository;

    public ResponseEntity<String> createUser(UserRequestDto responseDto) {
        return new ResponseEntity<>("생성 완료", HttpStatus.CREATED);
    }
}
