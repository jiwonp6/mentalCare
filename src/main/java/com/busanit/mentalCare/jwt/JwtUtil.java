package com.busanit.mentalCare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // SHA 256 암호화 알고리즘으로 생성한 키 (너무 약한 비밀번호는 불가)
    private final String secret = "ec81721d210b7a86f4536721495982d974c71237c4fc0ad37d3ccdfd925cceb6";      // 암호키
    private final long expiration = 86400000;    // 토큰 만료기간 (하루)

    // 비밀 키 생성
    private SecretKey getSingingKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 특정 클레임 추출
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    // 토큰에서 사용자 이름 추출
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 토큰에서 만료 날짜 추출
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 토큰 만료 여부 확인
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 토큰 생성
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)      // 클레임 설정
                .setSubject(subject)    // 사용자 이름 설정
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 토큰 발급시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 토큰 만료 시간
                .signWith(getSingingKey(), SignatureAlgorithm.HS256)    // 비밀키, 알고리즘
                .compact(); // 생성한 토큰을 문자열로 컴팩트
    }

    // 토큰 유효성 검증
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        Boolean isEqualUsername = username.equals(userDetails.getUsername());
        Boolean isNotExpired = !isTokenExpired(token);
        return isEqualUsername && isNotExpired;
    }
}
