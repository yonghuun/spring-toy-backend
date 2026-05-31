package com.toy.auth.user.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.auth.user.dto.LoginRequest;
import com.toy.auth.user.dto.SignupRequest;
import com.toy.auth.user.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	// 회원 가입
	@PostMapping("/signup")
	public String signup(@Valid @RequestBody SignupRequest request) {
		
		authService.signup(request);
		
		return "회원가입 완료";
	}
	
	// 로그인
	@PostMapping("/login")
	public Map<String, String> login(@Valid @RequestBody LoginRequest request){
		String token = authService.login(request);
		return Map.of("token", token);
	}
	
}
