package com.utility;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Users;
import com.repositry.UserService;

@Component
public class UsersUtility {
	
	@Autowired
    UserService repository;
	
	public String EncriptPassword(String pwd) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		byte[] bytesOfMessage = pwd.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		BigInteger bigInt = new BigInteger(1,thedigest);
		String hashtext = bigInt.toString(16);
		return hashtext;
	}
	
	public boolean CheckUserExcist(String email){
		
		if(repository.findByEmail(email)!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean RegisterUser(Users user){
		
		repository.save(user);
		
		return false;
		
	}
	

}
