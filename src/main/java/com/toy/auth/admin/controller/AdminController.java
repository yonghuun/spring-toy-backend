package com.toy.auth.admin.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.auth.admin.dto.AdminUpdateRoleRequest;
import com.toy.auth.admin.dto.AdminUserResponse;
import com.toy.auth.admin.service.AdminService;

import jakarta.validation.Valid;
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
	
	@PatchMapping("/users/{userId}/role")
	public AdminUserResponse updateUserRole(Authentication auth, @PathVariable Long userId, @Valid @RequestBody AdminUpdateRoleRequest request) {
		return adminService.updateUserRole(auth.getName(), userId, request);
	}
	
	@DeleteMapping("/users/{userId}")
	public void softDeleteUser(Authentication auth, @PathVariable Long userId) {
		adminService.softDeleteUser(auth.getName(), userId);
	}

	@PatchMapping("/users/{userId}/restore")
	public AdminUserResponse restoreUser(Authentication auth, @PathVariable Long userId) {
		return adminService.restoreUser(auth.getName(), userId);
	}
}