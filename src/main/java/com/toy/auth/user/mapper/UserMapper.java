package com.toy.auth.user.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.toy.auth.user.domain.User;

@Mapper
public interface UserMapper {
	
	// username으로 사용자 조회
	User findByUsername(String username);
	
	// 회원가입
	void insertUser(User user);
	
	List<User> findAll();

	User findById(@Param("userId") Long userId);

	void updateRole(@Param("userId") Long userId, @Param("role") String role);

	void softDeleteUser(@Param("userId") Long userId);

	void restoreUser(@Param("userId") Long userId);


}
