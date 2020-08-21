package com.example.SpringInitial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringInitial.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByReadUsers_UsernameIn(List<String> usernames);
	
	List<Book> findByFavoriteUsers_UsernameIn(List<String> usernames);
}
