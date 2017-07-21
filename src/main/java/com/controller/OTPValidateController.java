package com.controller;

import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.model.Users;
import com.repositry.UserService;
import com.utility.TimeBasedOneTimePasswordUtil;



@Controller
public class OTPValidateController {

	@Autowired
    UserService repository;
	
	@RequestMapping(value="/verifyOTP",method=RequestMethod.POST)
	public String VerifyOTP(@RequestParam String otp,@RequestParam Long id,Model model) throws GeneralSecurityException{
		
		
		Users u=repository.findOne(id);
		String otpgen=TimeBasedOneTimePasswordUtil.generateCurrentNumberString(u.getSecrect());
		System.out.println(u.getSecrect());
				
		if(otp.equalsIgnoreCase(otpgen)){
		    model.addAttribute("infomsg","Two Factore Authendication Enabled Success");	
			return "login";
		}else{
			model.addAttribute("Uid",id);
			return "failed";
		}
		
		
	}
	
	@RequestMapping(value="/verifyOTP",method=RequestMethod.GET)
	public String VerifyOTPGET(@RequestParam Long id,Model model){

		Users u=repository.findOne(id);
		if(u.getSecrect()==null){
		u.setSecrect(TimeBasedOneTimePasswordUtil.generateBase32Secret());
		u.setTwoFactorAuth("YES");
		repository.save(u);
		String qrurl=TimeBasedOneTimePasswordUtil.qrImageUrl("ConfigTip ( "+u.getEmail()+" )",u.getSecrect());
		model.addAttribute("QRCode",qrurl);
		model.addAttribute("Uid",u.getID());		
		return "regotp";
		}else{
			
			return "login";
		}
				
	}
	
	
}
