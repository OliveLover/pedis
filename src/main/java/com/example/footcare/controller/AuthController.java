package com.example.footcare.controller;

import com.example.footcare.dto.AuthResponseDto;
import com.example.footcare.dto.AuthSignUpRequestDto;
import com.example.footcare.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/v1/join")
    public ResponseEntity<AuthResponseDto> createUser(@Valid @RequestBody AuthSignUpRequestDto requestDto) {
        return authService.createUser(requestDto);
    }

}
