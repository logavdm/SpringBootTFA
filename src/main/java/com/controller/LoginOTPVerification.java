package com.controller;

import java.security.GeneralSecurityException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Users;
import com.repositry.UserService;
import com.utility.TimeBasedOneTimePasswordUtil;

@Controller
public class LoginOTPVerification {

	@Autowired
	UserService repository;
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/loginOTP")
	public String VerifyLoginOTP(@RequestParam String otp,@RequestParam Long id,Model model,HttpSession session) throws GeneralSecurityException{
	
		Users u=repository.findOne(id);
		if(otp.equalsIgnoreCase(TimeBasedOneTimePasswordUtil.generateCurrentNumberString(u.getSecrect()))){
			session.putValue("User",u);
			model.addAttribute("Uid",u.getID());
			model.addAttribute("email",u.getEmail());
			model.addAttribute("secret",u.getSecrect());
			model.addAttribute("TFA",u.getTwoFactorAuth());
			return "profile";
			
		}else{
			
			model.addAttribute("Uid",id);
			model.addAttribute("errormsg","Invalid OTP Please Wait for New OTP");
			return "otp";
			
		}
		
		
	}
}
