package com.example.footcare.controller;

import com.example.footcare.dto.UserRequestDto;
import com.example.footcare.dto.UserResponseDto;
import com.example.footcare.service.AuthService;
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
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto responseDto) {
        return authService.createUser(responseDto);
    }
}
