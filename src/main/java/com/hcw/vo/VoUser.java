package com.hcw.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class VoUser {

	@NotEmpty(message="名字不能为空")
	private String name;
	
	@NotEmpty(message="密码不能为空")
	@Pattern(regexp="^[a-zA-Z]\\w{6,12}$",message="以字母开头，长度在6~13之间，只能包含字母、数字和下划线")
	private String password1;
	
	@NotEmpty(message="第二次输入不能为空")
	private String password2;
	
	
	
	public VoUser(String name, String password1, String password2) {
		super();
		this.name = name;
		this.password1 = password1;
		this.password2 = password2;
	}

	@Override
	public String toString() {
		return "VoUser [name=" + name + ", password1=" + password1 + ", password2=" + password2 + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public VoUser() {
		super();
	}
		
}
