package com.example.SpringInitial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringInitial.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	boolean existsByName(String name);
	
	Role findByName(String name);
}
