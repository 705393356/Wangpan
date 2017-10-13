package com.hcw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcw.dao.UserDao;
import com.hcw.model.User;
import com.hcw.vo.VoUser;


@Service
public class RegisterService {

	@Autowired
	private UserDao userDao;
	
	public boolean checkPTP(VoUser user) {
		boolean result = false;
		
		if(user.getPassword1().equals(user.getPassword2())) {
			result = true;
		}
		return result;
	}
	

	public void addUser(String name,String username, String password) {
		User user = new User(name,username,password,0);
		userDao.AddUser(user);
	}
	
	
}
