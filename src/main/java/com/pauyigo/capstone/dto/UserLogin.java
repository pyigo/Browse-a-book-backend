package com.pauyigo.capstone.dto;

import javax.validation.constraints.*;

public class UserLogin {
	
	private String email;
	private String password;
	
	@Email(message = "Please enter a valid email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotNull(message = "Please enter a valid password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
