package com.example.SpringInitial.controller;

import java.util.Arrays;
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
import com.example.SpringInitial.entity.User;
import com.example.SpringInitial.model.UserBookDTO;
import com.example.SpringInitial.model.UserDTO;
import com.example.SpringInitial.model.UserUpdateDTO;
import com.example.SpringInitial.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> get(@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<User> users = userService.findAllActive(pageSize, pageNumber);
		
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	@ResponseBody
	@PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> get(@PathVariable long id) {
		Optional<User> userOptional = userService.findByIdActive(id);
		if (userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		}

		throw new IllegalArgumentException("User is not found");
	}

	@GetMapping("/search")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> get(@RequestParam(name = "username", defaultValue = "") String username,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<User> users = userService.findByUsernameActive(username, pageSize, pageNumber);

		return ResponseEntity.ok(users);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody UserUpdateDTO userDTO) {
		User user = userService.update(id, userDTO);

		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable long id) {
		User user = userService.delete(id);

		return ResponseEntity.ok(user);
	}

	@PostMapping("")
	@ResponseBody
	public ResponseEntity<?> post(@Valid @RequestBody UserDTO userDTO) {
		User user = userService.save(userDTO);

		return ResponseEntity.ok(user);
	}

	// ------------------ List Operations ---------------------- //

	@PostMapping("/add-read-item")
	@ResponseBody
	@PreAuthorize("#dto.userID == authentication.principal.id")
	public ResponseEntity<?> addRead(@Valid @RequestBody UserBookDTO dto) {
		User user = userService.addRead(dto);

		return ResponseEntity.ok(user);
	}

	@PostMapping("/add-favorite-item")
	@ResponseBody
	@PreAuthorize("#dto.userID == authentication.principal.id")
	public ResponseEntity<?> addFavorite(@Valid @RequestBody UserBookDTO dto) {
		User user = userService.addFavorite(dto);

		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/delete-read-item")
	@ResponseBody
	@PreAuthorize("#dto.userID == authentication.principal.id")
	public ResponseEntity<?> deleteRead(@Valid @RequestBody UserBookDTO dto) {
		User user = userService.deleteRead(dto);

		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/delete-favorite-item")
	@ResponseBody
	@PreAuthorize("#dto.userID == authentication.principal.id")
	public ResponseEntity<?> deleteFavorite(@Valid @RequestBody UserBookDTO dto) {
		User user = userService.deleteFavorite(dto);

		return ResponseEntity.ok(user);
	}

	@GetMapping("/read-list/{id}")
	@ResponseBody
	@PreAuthorize("#id == authentication.principal.id")
	public ResponseEntity<?> findRead(@PathVariable long id,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<Book> books = userService.findRead(id, pageSize, pageNumber);

		return ResponseEntity.ok(books);
	}

	@GetMapping("/favorite-list/{id}")
	@ResponseBody
	@PreAuthorize("#id == authentication.principal.id")
	public ResponseEntity<?> findFavorite(@PathVariable long id,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<Book> books = userService.findFavorite(id, pageSize, pageNumber);

		return ResponseEntity.ok(books);
	}
}
