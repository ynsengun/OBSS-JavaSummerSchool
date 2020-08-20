package com.example.SpringInitial.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringInitial.cache.UserCache;
import com.example.SpringInitial.cache.UserCachePrototype;
import com.example.SpringInitial.cache.UserCacheSingleton;
import com.example.SpringInitial.entity.User;
import com.example.SpringInitial.model.MyUserDetails;
import com.example.SpringInitial.model.UserDTO;
import com.example.SpringInitial.model.UserUpdateDTO;
import com.example.SpringInitial.repo.RoleRepository;
import com.example.SpringInitial.repo.UserDAO;
import com.example.SpringInitial.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
//	@Autowired
//	private ApplicationContext context;
//	@Autowired
//	@Qualifier("singletonCache")
//	private UserCache userCache;
//	@Autowired
//	private UserDAO userDAO;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
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
		user.setPassword(encoder.encode(userDto.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		
		User savedUser = userRepository.save(user);
		
		return savedUser;
	}
	
	public Page<User> findAll(int pageSize, int pageNumber){
//		return userDAO.get(pageSize, pageNumber);
		Pageable paged = PageRequest.of(pageNumber, pageSize);
		return userRepository.findAll(paged);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public List<User> findByRoles(List<String> roles){
		return userRepository.findByRoles_NameIn(roles);
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> byUserName = userRepository.findByUsername(username);
		if(byUserName.isPresent()) {
			return new MyUserDetails(byUserName.get());
		}
		throw new UsernameNotFoundException("User is not found");
	}
}
