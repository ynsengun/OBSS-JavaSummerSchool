package com.example.SpringInitial.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER")
public class User extends EntityBase {

	@Column(name = "USERNAME", length = 255, unique = true)
	private String username;

	@Column(name = "PASSWORD", length = 255)
	private String password;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "USERS_ROLES",
			joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") },
			inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	@JsonManagedReference
	private Set<Role> roles;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "USERS_BOOKS_READ",
			joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") },
			inverseJoinColumns = { @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID") })
	@JsonManagedReference
	private Set<Book> readList;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "USERS_BOOKS_FAVORITE",
			joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") },
			inverseJoinColumns = { @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID") })
	@JsonManagedReference
	private Set<Book> favoriteList;

	public Set<Book> getReadList() {
		return readList;
	}

	public void setReadList(Set<Book> readList) {
		this.readList = readList;
	}

	public Set<Book> getFavoriteList() {
		return favoriteList;
	}

	public void setFavoriteList(Set<Book> favoriteList) {
		this.favoriteList = favoriteList;
	}

	public String getUsername() {
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> list) {
		this.roles = list;
	}
}
