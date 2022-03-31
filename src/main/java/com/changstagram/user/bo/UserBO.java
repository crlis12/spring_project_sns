package com.changstagram.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changstagram.user.dao.UserDAO;
import com.changstagram.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;

	public int existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}

	public User getUserByLoginIdAndPassword(String loginId, String password) {

		return userDAO.selectUserByLoginIdAndPassword(loginId, password);
	}

	
	public User getUserByUserId(int userId) {
	  
		return userDAO.selectUserByUserId(userId); 
	}
	
	public int addUser(String loginId, String password, String name, String email) {
		return userDAO.insertUser(loginId, password, name, email);
	}
	
	// 비밀번호 변경
	public int upadateUser(String loginId, String password) {
		
		return userDAO.upadateUser(loginId, password);
	}
}
