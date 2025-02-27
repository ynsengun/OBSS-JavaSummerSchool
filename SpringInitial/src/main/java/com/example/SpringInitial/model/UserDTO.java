package com.example.SpringInitial.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
	
	@NotBlank
	@Email
	@Size(max = 255, min = 3, message = "Invalid user name")
	private String username;
	
	@NotBlank
	@Size(max = 255, min = 3, message = "Invalid password")
	private String password;

	public String getUserName() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
