package com.toy.auth.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMeResponse {
	
	private String username;
	private String role;
	
}
