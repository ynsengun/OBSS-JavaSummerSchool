package com.example.SpringInitial.cache;

import java.util.Map;

import com.example.SpringInitial.model.UserDTO;

public interface UserCache {
	void put(UserDTO user);

	Map<String, UserDTO> getMap();
}
