package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping(value="/logout")
	public String LogoutControllerMethod(HttpSession session){
		
		if(session.getAttribute("User")!=null){
			
			session.invalidate();
			return "login";
		}else{
		
			return "profile";
		}
	}
	
}
