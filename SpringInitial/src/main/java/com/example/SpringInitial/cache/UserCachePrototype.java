package com.example.SpringInitial.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.SpringInitial.model.UserDTO;

@Component("prototypeCache")
@Scope("prototype")
public class UserCachePrototype implements UserCache {
	private Map<String, UserDTO> users;
	
	@PostConstruct
	public void init() {
		users = new HashMap<>();
	}

	@Override
	public void put(UserDTO user) {
		users.put(user.getUserName(), user);
	}

	@Override
	public Map<String, UserDTO> getMap() {
		return this.users;
	}
}
