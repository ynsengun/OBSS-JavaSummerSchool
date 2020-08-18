package com.example.SpringInitial.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.SpringInitial.model.UserDTO;

@Component
@Scope("prototype")
public class UserCachePrototype {
	public Map<String, UserDTO> users;
	
	@PostConstruct
	public void init() {
		users = new HashMap<>();
	}
}
