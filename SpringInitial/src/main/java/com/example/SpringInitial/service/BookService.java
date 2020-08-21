package com.example.SpringInitial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.SpringInitial.entity.Book;
import com.example.SpringInitial.entity.User;
import com.example.SpringInitial.model.BookDTO;
import com.example.SpringInitial.model.BookUpdateDTO;
import com.example.SpringInitial.model.UserUpdateDTO;
import com.example.SpringInitial.repo.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public Page<Book> findAll(int pageSize, int pageNumber){
		Pageable paged = PageRequest.of(pageNumber, pageSize);
		return bookRepository.findAll(paged);
	}
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Book save(BookDTO bookDTO) {
		
		Book book = new Book();
		book.setName(bookDTO.getName());
		book.setAuthor(bookDTO.getAuthor());
		book.setType(bookDTO.getType());
		book.setDescription(bookDTO.getDescription());
		book.setPageNumber(bookDTO.getPageNumber());
		
		return bookRepository.save(book);
	}
	
	public Book update(long id, BookUpdateDTO dto) {
		Optional<Book> byId = bookRepository.findById(id);
		if(byId.isPresent()) {
			Book book = byId.get();
			book.setDescription(dto.getDescription());
			return bookRepository.save(book);
		}
		throw new IllegalArgumentException("Book is not found");
	}
	
	public Book delete(long id) {
		Optional<Book> byId = bookRepository.findById(id);
		if(byId.isPresent()) {
			Book book = byId.get();
			book.setActive(!book.isActive());
			return bookRepository.save(book);
		}
		throw new IllegalArgumentException("Book is not found");
	}
	
}
