package com.example.footcare.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.footcare.config.auth.UserDetailsImpl;
import com.example.footcare.model.Users;
import com.example.footcare.repository.AuthRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthRepository authRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader(JwtProperties.HEADER_STRING);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        System.out.println(token);

        String username = JWT.require(Algorithm.HMAC512("cocoball")).build()
                        .verify(token)
                                .getClaim("username")
                                        .asString();
        System.out.println(username);

        if (username != null) {
            Users user = authRepository.findByUsername(username).orElseThrow(() ->
                    new UsernameNotFoundException("존재하지 않는 유저입니다.")
            );

            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    null
            );
            System.out.println("authentication = " + authentication.getName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("SecurityContextHolder 이후 : " + SecurityContextHolder.getContext().getAuthentication().getName());
        }
        chain.doFilter(request, response);
    }
}
