package com.toy.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
		
		// Authorization 헤더 조회
		String authHeader = request.getHeader("Authorization");
		
		// Bearer 토큰인지 확인
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			// 실제 JWT 추출
			String token = authHeader.substring(7);
			
			// JWT 검증
			if(jwtUtil.validate(token)) {
				// 사용자 정보 추출
				String username = jwtUtil.getUsername(token);
				String role = jwtUtil.getRole(token);
				
				// Spring Security 인증 객체 생성
				UsernamePasswordAuthenticationToken auth = 
				    new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority(role)));				

				// 인정 정보 저장 - 핵심 코드
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
