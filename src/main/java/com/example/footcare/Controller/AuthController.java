package com.example.footcare.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    @PostMapping("/auth")
    public ResponseEntity<String> createUser() {
        return ResponseEntity.ok("생성 완료");
    }
}
