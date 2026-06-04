package com.toy.auth.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	// 사용자 pk
	private Long id;

	// 로그인 아이디
	private String username;

	// BCrypt 암호화 비밀번호
	private String password;

	// 사용자 권한
	private String role;

	// 사용자 상태 (ACTIVE, DELETED)
	private String status;

	// 삭제된 사용자일 경우 삭제 시간 기록
	private String deletedAt;
}
