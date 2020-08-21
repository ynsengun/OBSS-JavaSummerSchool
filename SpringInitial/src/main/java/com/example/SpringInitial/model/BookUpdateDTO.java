package com.example.SpringInitial.model;

import javax.validation.constraints.NotBlank;

public class BookUpdateDTO {
	
	@NotBlank
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
