package com.example.shoppinglist2.web;


import com.example.shoppinglist2.domain.User;
import com.example.shoppinglist2.domain.UserRepository;
import com.example.shoppinglist2.dto.GoogleTokenRequest;
import com.example.shoppinglist2.security.JwtService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RequiredArgsConstructor
@RestController
public class GoogleLoginController {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    // Google API console에서 발급받은 클라이언트 ID 주입
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    // 가정: 프론트엔드에서 ID Token 문자열을 담아 요청할 DTO
    // public record GoogleTokenRequest(String idToken) {}
    // 이 DTO는 별도로 구현해야 합니다. (이 예시에서는 idToken만 있다고 가정)


    @PostMapping("/api/auth/google")
    public ResponseEntity<?> authenticateGoogle(@RequestBody GoogleTokenRequest tokenRequest) {

        // 1. Google ID Token 검증기 설정
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        GoogleIdToken idToken;
        try {
            // 2. ID Token 검증 및 파싱
            idToken = verifier.verify(tokenRequest.idToken());
        } catch (GeneralSecurityException | IOException e) {

            // 검증 실패: 토큰이 유효하지 않음
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google ID Token.");
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // 3. 사용자 식별 정보 추출 (대부분의 경우 email 사용)
            String email = payload.getEmail();

            if (email == null) {
                // 이메일이 없을 경우, 고유한 Sub 값 사용 (필요시 DB 로직에 맞게 조정)
                email = payload.getSubject();
            }

            // TODO: 4. DB 사용자 인증/등록 로직 (현재는 생략)
            // - email을 사용하여 DB에서 사용자를 조회합니다.
            // - 사용자가 없으면 회원가입 처리(자동 생성)를 합니다.
            // - 이 과정에서 Spring Security의 UserDetailsService를 통해 사용자를 로드해야 합니다.
            String username = email; // JWT에 사용할 식별자로 이메일 사용

            // 5. 서버 JWT 생성

            String jwt = jwtService.generateToken(username);
            if (userRepository.findByUsername(username).isEmpty()) {
                User user = new User(username, "", "USER");
                userRepository.save(user);
            }
            // 6. 서버 JWT를 "Authorization" 헤더에 담아서 응답
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                    .build();

        } else {
            // 7. 검증은 통과했으나 파싱 문제 등으로 ID Token이 null인 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google ID Token format or content.");
        }
    }
}