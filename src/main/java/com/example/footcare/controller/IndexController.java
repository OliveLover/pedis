package com.example.footcare.controller;

import com.example.footcare.config.auth.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/auth")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/gallery")
    public String gallery(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails.getUsername());
        return "gallery";
    }
}
