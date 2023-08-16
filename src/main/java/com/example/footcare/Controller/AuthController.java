package com.example.footcare.Controller;

import com.example.footcare.Dto.UserRequestDto;
import com.example.footcare.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDto responseDto) {
        return authService.createUser(responseDto);
    }
}
