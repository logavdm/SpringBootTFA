package com.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.model.Users;
import com.repositry.UserService;
import com.utility.TimeBasedOneTimePasswordUtil;
import com.utility.UsersUtility;

@Controller
public class RegisterController {

	@Autowired
    UserService repository;
	@Autowired
	TimeBasedOneTimePasswordUtil twoFactorAuthUtil;
	@Autowired
	UsersUtility Userutility;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String RegisterUser(@RequestParam String email,@RequestParam String pwd,Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(!Userutility.CheckUserExcist(email)){	
			Users u=new Users();
			u.setEmail(email);
			u.setPassword(Userutility.EncriptPassword(pwd));
			u.setTwoFactorAuth("NO");
			u.setSecrect(null);
			Userutility.RegisterUser(u);	
			model.addAttribute("infomsg","Registration Success");
			return "login";
			
				
		}else{
			
			model.addAttribute("errormsg","Email ID Already Registred");
			return "register";
		}
			
			

			
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registerControllerGET(){
		
		return "register";
	}
	
	
	
	
	
	
}
