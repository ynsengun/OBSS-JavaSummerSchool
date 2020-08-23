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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "USER")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
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
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserBookRead> userBookReads;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserBookFavorite> userBookFavorites;

	public Set<UserBookFavorite> getUserBookFavorites() {
		return userBookFavorites;
	}

	public void setUserBookFavorites(Set<UserBookFavorite> userBookFavorites) {
		this.userBookFavorites = userBookFavorites;
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

	public Set<UserBookRead> getUserBookReads() {
		return userBookReads;
	}

	public void setUserBookReads(Set<UserBookRead> userBookReads) {
		this.userBookReads = userBookReads;
	}
}
