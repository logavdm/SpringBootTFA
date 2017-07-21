package com.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	private String email;
 
	private String Password;

	private String Secrect;
	
	private String TwoFactorAuth;

	public String getTwoFactorAuth() {
		return TwoFactorAuth;
	}

	public void setTwoFactorAuth(String twoFactorAuth) {
		TwoFactorAuth = twoFactorAuth;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		email = Email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSecrect() {
		return Secrect;
	}

	public void setSecrect(String secrect) {
		Secrect = secrect;
	}
	
 
	

}
