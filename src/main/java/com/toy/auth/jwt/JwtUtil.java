package com.toy.auth.jwt;

import io.jsonwebtoken.*;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	
	private final String SECRET = "springbootjwtsecretkeyspringbootjwtsecretkey";
	
	private final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	// JWT 생성
	public String createToken(String username, String role) {
		
		long now = System.currentTimeMillis();
		
		return Jwts.builder()
				// 사용자 아이디 저장
				.setSubject(username)
				
				// 사용자 권한 저장
				.claim("role", role)
				
				// 	발급 시간
				.setIssuedAt(new Date(now))
				
				// 만료 시간 (30분)
				.setExpiration(new Date(now + 1000 * 60 * 30))
				
				// 서명
				.signWith(KEY, SignatureAlgorithm.HS256)
				
				.compact();
	}
	
	// JWT 검증
	public boolean validate(String token) {
		try {

            Jwts.parser()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;
        }
	}
	
	// username 추출
	public String getUsername(String token) {
	
	    return Jwts.parser()
	            .setSigningKey(KEY)
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject();
	}
	
	// role 추출
	public String getRole(String token) {
	
	    return Jwts.parser()
	            .setSigningKey(KEY)
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .get("role", String.class);
	}
	

}
