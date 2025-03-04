package com.github.payment_manager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.github.payment_manager.domain.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        if (user != null) {
            try {
                String login = user.getLogin();
                System.out.println("Login: " + login);

                Algorithm algorithm = Algorithm.HMAC256(secret);
                String token = JWT.create()
                        .withIssuer("auth-api")
                        .withSubject(login)
                        .withExpiresAt(genExpirationDate())
                        .sign(algorithm);
                return token;
            } catch (JWTCreationException exception) {
                throw new RuntimeException("Error while generating token", exception);
            }
        } else {
            return null;
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String subject = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

            System.out.println("Subject: " + subject);
            return subject;
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}