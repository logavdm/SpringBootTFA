package com.controller;




import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Users;


@Controller
public class DemoController {
	
	

	@RequestMapping("/")
	public String GetQRCode(Model model,HttpSession session) {
		
		if(session.getAttribute("User")!=null){
			Users u=(Users) session.getAttribute("User");
			model.addAttribute("Uid",u.getID());
			model.addAttribute("email",u.getEmail());
			model.addAttribute("secret",u.getSecrect());
			model.addAttribute("TFA",u.getTwoFactorAuth());
			return "profile";
		}else
		return "login";
	}
	
	
	
}
