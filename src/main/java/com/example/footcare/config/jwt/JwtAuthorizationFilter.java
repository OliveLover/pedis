//package com.example.footcare.config.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//public class JwtAuthorizationFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//
//        System.out.println(jwtUtil.generateToken(authentication););
//
//        try {
//            DecodedJWT decodedJWT;
//            Algorithm algorithm = Algorithm.HMAC512("cocoball");
//            String token = JWT.create()
//                    .withIssuer("kimsubak")
//                    .sign(algorithm);
//            System.out.println("발급된 토큰 확인 :" + token);
//
//            System.out.println("-----------------------------");
//
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("kimsubak")
//                    .build();
//            decodedJWT = verifier.verify(token);
//            System.out.println("verifier = " + verifier);
//            System.out.println("decodedJWT = " + decodedJWT);
//
//            if(token.equals(decodedJWT.getToken())) {
//                System.out.println("일치");
//                System.out.println("decodedJWT.getHeader() : " + decodedJWT.getHeader());
//                System.out.println("decodedJWT.getPayload() : " + decodedJWT.getPayload());
//                System.out.println("decodedJWT.getSignature() :" + decodedJWT.getSignature());
//                System.out.println("decodedJWT.getAlgorithm() : " + decodedJWT.getAlgorithm());
//                System.out.println("decodedJWT.getIssuer() : " + decodedJWT.getIssuer());
//
//            } else {
//                System.out.println("불일치");
//            }
//        } catch (JWTCreationException e) {
//            System.err.println(e.getMessage());
//        }
//        chain.doFilter(request, response);
//    }
//}
