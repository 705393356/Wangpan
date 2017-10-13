package com.hcw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcw.model.User;
import com.hcw.service.CosService;
import com.hcw.service.UserService;
import com.hcw.vo.FilePath;

import net.sf.json.JSONObject;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private CosService cosService;

	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public JSONObject loginUp(@RequestBody User user) {

		JSONObject json = new JSONObject();
		User checkUser = userService.CheckLogin(user.getUsername());

		System.out.println(user);

		if (checkUser != null) {
			if ((checkUser.getPassword()).equals(user.getPassword())) {
				json.put("status", 1000);
				json.put("data", checkUser);
				FilePath path = new FilePath(user.getUsername());
				json.put("files", cosService.loginlist(path));
			}else {
				json.put("status", 3000);
				json.put("data","密码错误");
			}
		} else {
			json.put("status", 2000);
			json.put("data", "账号不存在");
		}

		return json;
	}
}
