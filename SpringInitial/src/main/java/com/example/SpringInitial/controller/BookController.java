package com.example.SpringInitial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringInitial.entity.Book;
import com.example.SpringInitial.model.BookDTO;
import com.example.SpringInitial.model.BookUpdateDTO;
import com.example.SpringInitial.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<?> get(@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<Book> books = bookService.findAllActive(pageSize, pageNumber);
		
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable long id) {
		Optional<Book> bookOptional = bookService.findByIdActive(id);
		if (bookOptional.isPresent()) {
			return ResponseEntity.ok(bookOptional.get());
		}

		throw new IllegalArgumentException("Book is not found");
	}
	
	@GetMapping("/search-name")
	@ResponseBody
	public ResponseEntity<?> getByName(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<Book> books = bookService.findByNameActive(name, pageSize, pageNumber);

		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/search-author")
	@ResponseBody
	public ResponseEntity<?> getByAuthor(@RequestParam(name = "author", defaultValue = "") String author,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<Book> books = bookService.findByAuthorActive(author, pageSize, pageNumber);

		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/search-type")
	@ResponseBody
	public ResponseEntity<?> getByType(@RequestParam(name = "type", defaultValue = "") String type,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<Book> books = bookService.findByTypeActive(type, pageSize, pageNumber);

		return ResponseEntity.ok(books);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody BookUpdateDTO bookDTO) {
		Book book = bookService.update(id, bookDTO);

		return ResponseEntity.ok(book);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Book book = bookService.delete(id);

		return ResponseEntity.ok(book);
	}
	
	@PostMapping("")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> post(@Valid @RequestBody BookDTO bookDTO){
		Book book = bookService.save(bookDTO);
		
		return ResponseEntity.ok(book);
	}
}
