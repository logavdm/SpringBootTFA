package com.controller;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.model.Users;
import com.repositry.UserService;
import com.utility.UsersUtility;


@Controller
public class LoginController {
	
	@Autowired
	UserService repository;
	@Autowired
	UsersUtility Userutility;
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String LoginValidate(@RequestParam String email,@RequestParam String pwd,Model model,HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		if(Userutility.CheckUserExcist(email)){
			
			Users u=(Users) repository.findByEmail(email);	
			if(u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(Userutility.EncriptPassword(pwd))){
				
				if(u.getTwoFactorAuth().equalsIgnoreCase("YES")){	
					model.addAttribute("Uid",u.getID());
					return "otp";		
				}else{
				model.addAttribute("Uid",u.getID());
				model.addAttribute("email",u.getEmail());
				model.addAttribute("secret",u.getSecrect());
				model.addAttribute("TFA",u.getTwoFactorAuth());
				session.putValue("User",u);
				
				Users u1=(Users) session.getAttribute("User");
				System.out.println(u1.getEmail());
				return "profile";
				}
				
			}else{
				model.addAttribute("errormsg","Invalid Username or Password");
				return "login";
			}
			
		}else{
			
			model.addAttribute("errormsg","cannot find any user name or password");
			return "login";
		}
		
	
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String LoginPage(HttpSession session,Model model){	
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
