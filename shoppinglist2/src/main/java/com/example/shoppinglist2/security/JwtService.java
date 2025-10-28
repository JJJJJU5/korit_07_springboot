package com.example.shoppinglist2.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    static final long EXPITATION_TIME = 86400000;
    static final String PREFIX = "Bearer ";

    private final SecretKey key = Jwts.SIG.HS256.key().build();

    // JWT 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPITATION_TIME))
                .signWith(key)
                .compact();
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token != null && token.startsWith(PREFIX)) {
            try {
                String user = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token.replace(PREFIX,""))
                        .getPayload()
                        .getSubject();
                if (user != null) {
                    return user;
                }

            } catch (Exception e ) {
                e.printStackTrace();
            }
        }
        return null; // getAuthUser 메서드 호출했는데 user 안나올 때 null return; 토큰이 없거나 유효하지 않음
    }
}
