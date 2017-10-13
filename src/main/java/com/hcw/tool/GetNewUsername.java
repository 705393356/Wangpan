package com.hcw.tool;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcw.dao.UserDao;
import com.hcw.service.UserService;

public class GetNewUsername {

	public static String getUsername(String id) {
		
		String username = "010";
		Calendar cal = Calendar.getInstance();
		String year ="" +cal.get(Calendar.YEAR) ;
		username = username + year.substring(2)+ cal.get(Calendar.MINUTE);
		System.out.println("ONEssssssssss:" + username);
		String second = ""+cal.get(Calendar.SECOND);
		if(second.length()==1) {
			second = 0 + second ;
		}
		username = username + second + id;
		System.out.println("SECONDssssssssss:" + username);
		
		return username;
	}
	
	
}
