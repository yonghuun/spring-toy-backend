package com.toy.auth.admin.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.auth.admin.dto.AdminUserResponse;
import com.toy.auth.admin.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;
	
	@GetMapping("/users")
	public List<AdminUserResponse> getUsers(Authentication auth) {
		return adminService.getUsers();
	}
	
	

}
