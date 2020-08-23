package com.example.SpringInitial.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "BOOK")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
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
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserBookRead> userBookReads;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserBookFavorite> userBookFavorites;
	
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

	public Set<UserBookRead> getUserBookReads() {
		return userBookReads;
	}

	public void setUserBookReads(Set<UserBookRead> userBookReads) {
		this.userBookReads = userBookReads;
	}

	public Set<UserBookFavorite> getUserBookFavorites() {
		return userBookFavorites;
	}

	public void setUserBookFavorites(Set<UserBookFavorite> userBookFavorites) {
		this.userBookFavorites = userBookFavorites;
	}
}
