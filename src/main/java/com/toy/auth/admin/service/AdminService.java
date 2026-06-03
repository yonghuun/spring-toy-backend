package com.toy.auth.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.toy.auth.admin.dto.AdminUserResponse;
import com.toy.auth.user.domain.User;
import com.toy.auth.user.mapper.UserMapper;

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

}
