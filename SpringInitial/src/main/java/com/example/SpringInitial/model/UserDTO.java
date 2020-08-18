package com.example.SpringInitial.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
	
	@NotBlank
	@Email
	@Size(max = 255, min = 3, message = "Invalid user name")
	private String userName;
	
	@NotBlank
	@Size(max = 255, min = 3, message = "Invalid password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
