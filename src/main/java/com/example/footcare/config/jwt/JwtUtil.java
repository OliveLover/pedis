package com.example.footcare.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(String username) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);

        String jwtToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("username", username)
                .withIssuer("noisy-rabbit")
                .sign(algorithm);

        return JwtProperties.TOKEN_PREFIX + jwtToken;
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println("token : " + token);

        if (!StringUtils.isEmpty(token) && token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return token.replace(JwtProperties.TOKEN_PREFIX, "");
        }

        return null;
    }

    public boolean validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("noisy-rabbit").build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.getStackTrace();
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        return JWT.require(algorithm).build().verify(token).getClaim("username").asString();
    }
}
