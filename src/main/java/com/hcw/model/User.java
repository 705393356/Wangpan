package com.hcw.model;


public class User {

	private int id;
	private String name;
	private String username;
	private String password;
	private int identity;
	
	
	public User(int id) {
		super();
		this.id = id;
	}

	public User(int id,String name) {
		this.id = id;
		this.name =name;
		
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(int id, String name, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	public User(int id, String name, String username, String password, int identity) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.identity = identity;
	}

	public User(String name, String username, String password, int identity) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.identity = identity;
	}

	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}

	
	
}
