package com.example.SpringInitial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.SpringInitial.entity.Role;
import com.example.SpringInitial.repo.RoleRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		boolean userRoleExists = roleRepository.existsByName("ROLE_USER");
		if (!userRoleExists) {
			Role userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);
		}

		boolean adminRoleExists = roleRepository.existsByName("ROLE_ADMIN");
		if (!adminRoleExists) {
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);
		}
	}

}
