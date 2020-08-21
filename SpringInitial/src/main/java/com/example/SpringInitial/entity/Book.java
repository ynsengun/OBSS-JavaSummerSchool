package com.example.SpringInitial.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "BOOK")
public class Book extends EntityBase {

	@Column(name = "NAME", length = 255)
	private String name;
	
	@Column(name = "AUTHOR", length = 255)
	private String author;
	
	@Column(name = "TYPE", length = 255)
	private String type;
	
	@Column(name = "PAGENUMBER")
	private long pageNumber;

	@Column(name = "DESCRIPTION", length = 255)
	private String description;
	
	@ManyToMany(mappedBy = "readList")
	@JsonBackReference
	private Set<User> readUsers;
	
	@ManyToMany(mappedBy = "favoriteList")
	@JsonBackReference
	private Set<User> favoriteUsers;
	
	public long getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getReadUsers() {
		return readUsers;
	}

	public void setReadUsers(Set<User> readUsers) {
		this.readUsers = readUsers;
	}

	public Set<User> getFavoriteUsers() {
		return favoriteUsers;
	}

	public void setFavoriteUsers(Set<User> favoriteUsers) {
		this.favoriteUsers = favoriteUsers;
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
