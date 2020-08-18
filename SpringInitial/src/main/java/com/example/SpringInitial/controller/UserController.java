package com.example.SpringInitial.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringInitial.cache.UserCachePrototype;
import com.example.SpringInitial.cache.UserCacheSingleton;
import com.example.SpringInitial.model.UserDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private ApplicationContext context;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<?> get(){
		int a  = 1/0;
		return ResponseEntity.ok("get-successful");
	}
	
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<?> post(@Valid @RequestBody UserDTO user){
		LOGGER.info("{} {}", user.getUserName(), user.getPassword());
		
		UserCachePrototype prototypeBean = context.getBean(UserCachePrototype.class);
		UserCacheSingleton singletonBean = context.getBean(UserCacheSingleton.class);
		
		prototypeBean.users.put(user.getUserName(), user);
		singletonBean.users.put(user.getUserName(), user);
		
		Map<String, Map> resultMap = new HashMap<>();
		resultMap.put("prototype", prototypeBean.users);
		resultMap.put("singleton", singletonBean.users);
		
		return ResponseEntity.ok(resultMap);
	}
	
}
