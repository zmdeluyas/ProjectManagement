package com.ciexperts.projectmanagement.service;

import java.util.HashMap;
import com.ciexperts.projectmanagement.dao.UserDao;
import com.ciexperts.projectmanagement.entity.User;
import com.ciexperts.projectmanagement.entity.UserRole;
import com.ciexperts.projectmanagement.tools.Util;

public class UserService {
	
	UserDao userDao = new UserDao();
	
	public HashMap<String, Object> authenticateUser(String username, String password){
		
		User user = userDao.findUser(username);
		HashMap<String, Object> userInfo = new HashMap<String,Object>();
		UserRole role = null;
		String access = null;
		String fullName = null;
		
		if(user != null){
			if(user.getPassword().equals(password)){
				fullName = user.getFirstName() +' '+ user.getLastName();
				System.out.println("USER SERVICE FULLNAME>>> " + fullName);
				role = userDao.getUserRole(user.getUserRole());
				access = Util.getAccess(role.getRoleName());
				userInfo.put("fullName", fullName);
				userInfo.put("access",access);
				userInfo.put("result","success");
			}else {
				System.out.println("USER LOGIN FAILED");
				userInfo.put("fullName", "");
				userInfo.put("access","");
				userInfo.put("result", "failed");
			}
		} else {
			System.out.println("USER LOGIN FAILED");
			userInfo.put("fullName", "");
			userInfo.put("access","");
			userInfo.put("result", "failed");
		}
		return userInfo;
	}
	
	public User findUser(String userId){
		return this.userDao.findUser(userId);
	}
	
}
