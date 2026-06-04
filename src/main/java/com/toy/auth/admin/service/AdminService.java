package com.toy.auth.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.toy.auth.admin.dto.AdminUpdateRoleRequest;
import com.toy.auth.admin.dto.AdminUserResponse;
import com.toy.auth.user.domain.User;
import com.toy.auth.user.mapper.UserMapper;
import com.toy.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final UserMapper userMapper;

	public List<AdminUserResponse> getUsers() {
		
		List<User> users = userMapper.findAll();
		
		List<AdminUserResponse> responses = new ArrayList<>();
		
		for(User user : users) {
			AdminUserResponse response = AdminUserResponse.from(user);
			responses.add(response);
		}
		
		return responses;
	}

	

	public AdminUserResponse updateUserRole(String adminUsername, Long userId, AdminUpdateRoleRequest request) {
		
		User targetUser = userMapper.findById(userId);
	    if (targetUser == null) {
	        throw new BusinessException("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
	    }

	    if (targetUser.getUsername().equals(adminUsername)) {
	        throw new BusinessException("자기 자신의 권한은 변경할 수 없습니다.", HttpStatus.BAD_REQUEST);
	    }
		
		String role = request.getRole();
		if(!role.equals("ROLE_USER") && !role.equals("ROLE_ADMIN")) {
		    throw new BusinessException("권한은 ROLE_USER 또는 ROLE_ADMIN만 가능합니다.", HttpStatus.BAD_REQUEST);
		}
		
		userMapper.updateRole(userId, role);
		
		User updatedUser = userMapper.findById(userId);
		
		return AdminUserResponse.from(updatedUser);
	}

}
