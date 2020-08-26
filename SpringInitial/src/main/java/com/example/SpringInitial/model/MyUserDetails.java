package com.example.SpringInitial.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.SpringInitial.entity.Role;
import com.example.SpringInitial.entity.User;

public class MyUserDetails implements UserDetails {
	private static final long serialVersionUID = 1084795066311163486L;

	private User user;
	
	public MyUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user != null ?
				user.getRoles().stream().map(t -> new SimpleGrantedAuthority(t.getName())).collect(Collectors.toList()):
					Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		if("admin@admin.com".equals(user.getUsername()))
			return true;
		return user.isActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		if("admin@admin.com".equals(user.getUsername()))
			return true;
		return user.isActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if("admin@admin.com".equals(user.getUsername()))
			return true;
		return user.isActive();
	}

	@Override
	public boolean isEnabled() {
		if("admin@admin.com".equals(user.getUsername()))
			return true;
		return user.isActive();
	}
	
	public long getId() {
		return this.user.getId();
	}
	
	public Set<Role> getRoles() {
		return this.user.getRoles();
	}

}
