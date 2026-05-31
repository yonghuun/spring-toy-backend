package com.toy.auth.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;

@Getter
public class SignupRequest {
	
	// 공백 불가, 최소 4자
	@NotBlank
	@Size(min=4, max=20)
	private String username;
	
	@NotBlank
	@Size(min=4, max=20)
	private String password;
	
}
