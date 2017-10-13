package com.hcw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcw.dao.UserDao;
import com.hcw.model.User;
import com.hcw.tool.GetNewUsername;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User CheckLogin(String username) {
		
		return userDao.CheckLogin(username);
	}
	
	public String CreateUsername() {
		
		List<User> list = userDao.CreateUsernameById();
		int num =0;
		for(User i : list) {
			if(i.getId()>num) {
				num = i.getId();
			}
		}
		String S_num = ""+num;
		String username = GetNewUsername.getUsername(S_num);
		return username;
	}
}
