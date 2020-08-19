package com.example.SpringInitial.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.example.SpringInitial.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByUsernameStartingWithOrderByIdDesc(String username);
	
	@Query("select u from User u where u.id = :id")
	Optional<User> getById(long id);
	
	@Query(value="select * from User u where u.id = :id", nativeQuery=true)
	Optional<User> getByIdNative(long id);
}
