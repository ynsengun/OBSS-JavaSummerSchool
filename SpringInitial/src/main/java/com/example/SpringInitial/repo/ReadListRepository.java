package com.example.SpringInitial.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringInitial.entity.UserBookRead;

@Repository
public interface ReadListRepository extends JpaRepository<UserBookRead, Long>  {
	
	Page<UserBookRead> findByUser_UsernameIn(List<String> usernames, Pageable pageable);
	
	Optional<UserBookRead> findByUser_IdInAndBook_IdIn(List<Long> userID, List<Long> bookID);
}
