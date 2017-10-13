package com.hcw.dao;

import java.util.List;

import com.hcw.model.User;

public interface UserDao {

	public User CheckLogin(String username);
	public List<User> CreateUsernameById();
	public void AddUser(User user);
	
}
