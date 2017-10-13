package com.hcw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcw.service.CosService;
import com.hcw.service.RegisterService;
import com.hcw.service.UserService;
import com.hcw.vo.FilePath;
import com.hcw.vo.VoUser;

import net.sf.json.JSONObject;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private CosService cosService;
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(){
		return "/register";
	}
	
	@ResponseBody
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public JSONObject registerCheck(@Valid @RequestBody VoUser user,BindingResult result) {
		JSONObject json = new JSONObject();
		String username = null;
		Map<String, String> map = new HashMap<String, String>();
		if(result.hasErrors()) {
			
			FieldError fielderror = (FieldError)result.getFieldErrors().get(0);
			//String error1 = "ERORR: !!!!!!!!" + fielderror.getDefaultMessage();
			json.put("status", 2000);
			json.put("username", username);
			json.put("message", fielderror.getDefaultMessage());
		}else {
			if(registerService.checkPTP(user)) {
				
				username = userService.CreateUsername();
				registerService.addUser(user.getName(),username,user.getPassword1());
				
				System.out.println("aluba" + username);
				map.put("username", username);
				json.put("status", 1000);
				json.put("message",map);
				cosService.createGreateFolder(username);
				cosService.registerFolder(username);
				FilePath path = new FilePath(username);
				String files = cosService.loginlist(path);
				json.put("files", files);
			}else {
				json.put("status", 3000);
				json.put("message","两次输入不正确");
			}
		}
		
		return json;
	}
}
