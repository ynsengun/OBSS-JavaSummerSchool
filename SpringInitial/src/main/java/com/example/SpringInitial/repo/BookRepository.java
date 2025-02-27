package com.example.SpringInitial.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringInitial.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	Page<Book> findByNameContainingAndActiveTrue(String name, Pageable pageable);
	
	Page<Book> findByAuthorContainingAndActiveTrue(String author, Pageable pageable);
	
	Page<Book> findByTypeContainingAndActiveTrue(String type, Pageable pageable);
	
	Optional<Book> findByIdAndActiveTrue(Long id);
	
	Page<Book> findByActiveTrue(Pageable pageable);
	
	Page<Book> findByActiveFalse(Pageable pageable);
}
