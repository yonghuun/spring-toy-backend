package com.toy.auth.user.service;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.toy.auth.jwt.JwtUtil;
import com.toy.auth.user.domain.User;
import com.toy.auth.user.dto.LoginRequest;
import com.toy.auth.user.dto.SignupRequest;
import com.toy.auth.user.mapper.UserMapper;
import com.toy.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtUtil jwtUtil;
	
	
	// 회원가입
	public void signup(SignupRequest request) {
		// 중복 사용자 검사
		User findUser = userMapper.findByUsername(request.getUsername());
		
		if(findUser != null) {
			throw new BusinessException("이미 존재하는 아이디입니다.", HttpStatus.CONFLICT);
		}
		
		// BCrypt 암호화 (매우 중요!)
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		
		// DB 저장 객체 생성
		User user = User.builder()
				.username(request.getUsername())
				.password(encodedPassword)
				/*
				 role -> 스프링 시큐리티는 사용자 권한을 체크할 때, 기본적으로 권한 이름 앞에 ROLE_ 이라는 접두사를 붙이는 것을 표준으로 인식
				 회원가입은 기본적으로 일반 유저 권한을 주기 때문에 회원가입 요청을 보낸 사람에게 ROLE_USER 지정
				 지금처럼 하드코딩하는 것도 동작에는 문제가 없지만, enum으로 관리하는 것이 좋기 때문 이후에 수정해보기.
				 */
				.role("ROLE_USER")
				.build();
		userMapper.insertUser(user);
	}
	
	//로그인
	public String login(LoginRequest request) {
		
		// DB 사용자 조회
		User user = userMapper.findByUsername(request.getUsername());
		
		if(user == null) {
			throw new BusinessException("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		}

		if("DELETED".equals(user.getStatus())) {
			throw new BusinessException("삭제된 사용자입니다.", HttpStatus.UNAUTHORIZED);
		}
		
		/* BCrypt 비밀번호 비교 - 절대 equals 사용 금지
		 * DB에는 암호화된 BCrypt 문자열이 저장되어 있기 때문에 반드시 passwordEncoder.matches() 사용
		 */
		boolean match = passwordEncoder.matches(request.getPassword(), user.getPassword());
		
		if(!match) {
			throw new BusinessException("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
		}
		
		// JWT 발급
		return jwtUtil.createToken(user.getUsername(), user.getRole());

	}

}
