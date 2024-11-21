package com.example.demo.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
//	private static final String SECRET_KEY ="42032237LukeJohn!!!!!!!^^";
	@Value("${jwt.secret}")
	private String secretKey;


    
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
                .signWith(SignatureAlgorithm.HS512, secretKey)
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
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)  // 서명 키를 설정
                    .parseClaimsJws(token)     // JWT 파싱
                    .getBody();

            // 만료된 토큰일 경우 처리
            if (claims.getExpiration().before(new Date())) {
                throw new IllegalArgumentException("Token has expired");
            }

            return claims.getSubject();
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT signature", e);  // 서명 불일치
        } catch (Exception e) {
            throw new RuntimeException("JWT parsing error", e);  // 기타 예외 처리
        }
    }

}
