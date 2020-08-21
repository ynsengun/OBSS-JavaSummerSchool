package com.example.SpringInitial.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class BookDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String author;
	
	@NotBlank
	private String type;
	
	@Min(1)
	private long pageNumber;
	
	@NotBlank
	private String description;

	public long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String username) {
		this.name = username;
	}
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
