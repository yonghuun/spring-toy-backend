package com.toy.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.toy.auth.jwt.JwtFilter;
import com.toy.auth.jwt.JwtUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtUtil jwtUtil;
	
	// Bcrypt Bean 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			// csrf 비활성화, RESP API는 보통 사용 안
			.csrf(csrf->csrf.disable())
			// JWT는 세션 사용 안 함
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
			// URL 권한 설정
			.authorizeHttpRequests(auth -> auth
					
					// 회원가입 / 로그인 허용
					.requestMatchers("/auth/signup", "/auth/login").permitAll()
					
					// 나머지는 인증 필요
					.anyRequest()
					.authenticated()
					
			)
			
			// JWT 필터 등록
			.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	

}
