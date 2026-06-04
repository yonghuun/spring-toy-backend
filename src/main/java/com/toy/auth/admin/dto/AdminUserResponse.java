package com.toy.auth.admin.dto;

import com.toy.auth.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminUserResponse {
	
	private long userId;
	private String username;
	private String role;
	
	public static AdminUserResponse from(User user) {
		return new AdminUserResponse(
				user.getId(),
				user.getUsername(),
				user.getRole()
		);
	}

}
