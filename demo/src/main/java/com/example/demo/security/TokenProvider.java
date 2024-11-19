package com.example.demo.security;

<<<<<<< HEAD
public abstract class TokenProvider {

=======
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	@Value("${jwt.secret}")
	private static String secretKey;

    // 비밀 키는 환경 변수나 설정 파일을 통해 안전하게 관리해야 합니다.
    private static final String SECRET_KEY = secretKey;

    /**
     * 사용자 정보를 기반으로 JWT 생성
     * 
     * @param userEntity 사용자 엔티티
     * @return 생성된 JWT 토큰
     */
    public String create(UserEntity userEntity) {
        // 기한을 지금으로부터 1일로 설정
        Date expiryDate = Date.from(
                Instant.now().plus(1, ChronoUnit.DAYS));

        // JWT Token 생성
        return Jwts.builder()
                // header에 들어갈 내용 및 서명을 하기 위한 secret_key
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                // payload에 들어갈 내용
                .setSubject(userEntity.getId()) // sub
                .setIssuer("demo app") // iss
                .setIssuedAt(new Date()) // iat
                .setExpiration(expiryDate) // exp
                .compact();
    }

    /**
     * JWT를 검증하고 사용자 ID 반환
     * 
     * @param token JWT 토큰
     * @return 사용자 ID
     */
    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
>>>>>>> ef2294caaa7ce2fa43673ebd368cd8c238f4c2b9
}
