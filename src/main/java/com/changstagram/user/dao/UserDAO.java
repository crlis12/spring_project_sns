package com.changstagram.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.changstagram.user.model.User;

@Repository
public interface UserDAO {

	//중복확인
	public int existLoginId(String loginId);
	
	// 로그인
	public User selectUserByLoginIdAndPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	public User selectUserByUserId(int userId);
	
	
	// 회원가입
	public int insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email);
	
	public int upadateUser(
			@Param("loginId") String loginId,
			@Param("password") String password);
}
