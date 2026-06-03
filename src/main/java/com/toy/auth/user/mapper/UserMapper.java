package com.toy.auth.user.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.toy.auth.user.domain.User;

@Mapper
public interface UserMapper {
	
	// username으로 사용자 조회
	User findByUsername(String username);
	
	// 회원가입
	void insertUser(User user);
	
	List<User> findAll();


}
