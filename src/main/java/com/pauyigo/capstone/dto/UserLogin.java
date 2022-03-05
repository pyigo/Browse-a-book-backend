package com.pauyigo.capstone.dto;

import javax.validation.constraints.*;

public class UserLogin {

	@Email(message = "Please enter a valid email")
	private String email;
	
	@NotNull(message = "Please enter a valid password")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
