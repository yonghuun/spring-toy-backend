package com.toy.auth.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdminUpdateRoleRequest {
	
    @NotBlank(message = "변경할 권한을 입력해주세요.")
	private String role;
}
