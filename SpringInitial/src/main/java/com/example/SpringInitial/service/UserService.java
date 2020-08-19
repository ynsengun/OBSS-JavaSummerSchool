package com.example.SpringInitial.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.SpringInitial.cache.UserCache;
import com.example.SpringInitial.cache.UserCachePrototype;
import com.example.SpringInitial.cache.UserCacheSingleton;
import com.example.SpringInitial.entity.User;
import com.example.SpringInitial.model.UserDTO;
import com.example.SpringInitial.model.UserUpdateDTO;
import com.example.SpringInitial.repo.UserDAO;
import com.example.SpringInitial.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	@Qualifier("singletonCache")
	private UserCache userCache;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDAO userDAO;
	
	public User save(UserDTO userDto){
		/*
		UserCachePrototype prototypeBean = context.getBean(UserCachePrototype.class);
		UserCacheSingleton singletonBean = context.getBean(UserCacheSingleton.class);
		
		prototypeBean.users.put(user.getUserName(), user);
		singletonBean.users.put(user.getUserName(), user);
		
		// --------------------------
		
		userCache.put(user);
		
		Map<String, Map> resultMap = new HashMap<>();
		resultMap.put("singleton", userCache.getMap());
		*/
		
		User user = new User();
		user.setUsername(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		
		User savedUser = userRepository.save(user);
		
		return savedUser;
	}
	
	public List<User> findAll(int pageSize, int pageNumber){
		return userDAO.get(pageSize, pageNumber);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}
	
	public List<User> findByUsername(String username){
		return userRepository.findByUsernameStartingWithOrderByIdDesc(username);
	}
	
	public User update(long id, UserUpdateDTO dto) {
		Optional<User> byId = userRepository.findById(id);
		if(byId.isPresent()) {
			User user = byId.get();
			user.setPassword(dto.getPassword());
			return userRepository.save(user);
		}
		throw new IllegalArgumentException("User is not found");
	}
	
	public User delete(long id) {
		Optional<User> byId = userRepository.findById(id);
		if(byId.isPresent()) {
			User user = byId.get();
			user.setActive(!user.isActive());
			return userRepository.save(user);
		}
		throw new IllegalArgumentException("User is not found");
	}
}
