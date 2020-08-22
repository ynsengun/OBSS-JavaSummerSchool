package com.example.SpringInitial.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringInitial.entity.Book;
import com.example.SpringInitial.entity.Role;
import com.example.SpringInitial.entity.User;
import com.example.SpringInitial.model.MyUserDetails;
import com.example.SpringInitial.model.UserBookDTO;
import com.example.SpringInitial.model.UserDTO;
import com.example.SpringInitial.model.UserUpdateDTO;
import com.example.SpringInitial.repo.BookRepository;
import com.example.SpringInitial.repo.RoleRepository;
import com.example.SpringInitial.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public User save(UserDTO userDto) {

		User user = new User();
		user.setUsername(userDto.getUserName());
		user.setPassword(encoder.encode(userDto.getPassword()));
		
		Role role;
		if("admin@admin.com".equals(user.getUsername())) {
			role = roleRepository.findByName("ROLE_ADMIN");
		} else {
			role = roleRepository.findByName("ROLE_USER");
		}
		
		Set<Role> set = new HashSet<>();
		set.add(role);
		user.setRoles(set);

		User savedUser = userRepository.save(user);

		return savedUser;
	}

	public Page<User> findAllActive(int pageSize, int pageNumber) {
		Pageable paged = PageRequest.of(pageNumber, pageSize);
		return userRepository.findByActiveTrue(paged);
	}

	public Optional<User> findByIdActive(long id) {
		return userRepository.findByIdAndActiveTrue(id);
	}

	public Page<User> findByUsernameActive(String username, int pageSize, int pageNumber) {
		Pageable paged = PageRequest.of(pageNumber, pageSize);
		return userRepository.findByUsernameContainingAndActiveTrue(username, paged);
	}

	public User update(long id, UserUpdateDTO dto) {
		Optional<User> byId = userRepository.findByIdAndActiveTrue(id);
		if (byId.isPresent()) {
			User user = byId.get();
			user.setPassword(dto.getPassword());
			return userRepository.save(user);
		}
		throw new IllegalArgumentException("User is not found");
	}

	public User delete(long id) {
		Optional<User> byId = userRepository.findById(id);
		if (byId.isPresent()) {
			User user = byId.get();
			user.setActive(!user.isActive());
			return userRepository.save(user);
		}
		throw new IllegalArgumentException("User is not found");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> byUserName = userRepository.findByUsername(username);
		if (byUserName.isPresent()) {
			return new MyUserDetails(byUserName.get());
		}
		throw new UsernameNotFoundException("User is not found");
	}
	
	// ------------------ List operations ----------------------- //

	public User addRead(UserBookDTO dto) {
		Optional<User> userById = userRepository.findByIdAndActiveTrue(dto.getUserID());
		
		if (userById.isPresent()) {
			Optional<Book> bookById = bookRepository.findByIdAndActiveTrue(dto.getBookID());
			
			if (bookById.isPresent()) {
				User user = userById.get();
				Book book = bookById.get();

				user.getReadList().add(book);

				return userRepository.save(user);
			}
			throw new UsernameNotFoundException("Book is not found");
		}
		throw new UsernameNotFoundException("User is not found");
	}

	public User addFavorite(UserBookDTO dto) {
		Optional<User> userById = userRepository.findByIdAndActiveTrue(dto.getUserID());

		if (userById.isPresent()) {
			Optional<Book> bookById = bookRepository.findByIdAndActiveTrue(dto.getBookID());
			
			if (bookById.isPresent()) {
				User user = userById.get();
				Book book = bookById.get();

				user.getFavoriteList().add(book);

				return userRepository.save(user);
			}
			throw new UsernameNotFoundException("Book is not found");
		}
		throw new UsernameNotFoundException("User is not found");
	}
	
	public User deleteRead(UserBookDTO dto) {
		Optional<User> userById = userRepository.findByIdAndActiveTrue(dto.getUserID());

		if (userById.isPresent()) {
			Optional<Book> bookById = bookRepository.findByIdAndActiveTrue(dto.getBookID());
			
			if (bookById.isPresent()) {
				User user = userById.get();
				Book book = bookById.get();

				user.getReadList().remove(book);

				return userRepository.save(user);
			}
			throw new UsernameNotFoundException("Book is not found");
		}
		throw new UsernameNotFoundException("User is not found");
	}
	
	public User deleteFavorite(UserBookDTO dto) {
		Optional<User> userById = userRepository.findByIdAndActiveTrue(dto.getUserID());

		if (userById.isPresent()) {
			Optional<Book> bookById = bookRepository.findByIdAndActiveTrue(dto.getBookID());
			
			if (bookById.isPresent()) {
				User user = userById.get();
				Book book = bookById.get();

				user.getFavoriteList().remove(book);

				return userRepository.save(user);
			}
			throw new UsernameNotFoundException("Book is not found");
		}
		throw new UsernameNotFoundException("User is not found");
	}
	
	public Page<Book> findRead(long userID, int pageSize, int pageNumber){
		Optional<User> userById = userRepository.findByIdAndActiveTrue(userID);
		
		if(userById.isPresent()) {
			User user = userById.get();
			Pageable paged = PageRequest.of(pageNumber, pageSize);
			
			return bookRepository.findByReadUsers_UsernameIn(Arrays.asList(user.getUsername()), paged); 
		}
		
		return null;
	}
	
	public Page<Book> findFavorite(long userID, int pageSize, int pageNumber){
		Optional<User> userById = userRepository.findByIdAndActiveTrue(userID);
		
		if(userById.isPresent()) {
			User user = userById.get();
			Pageable paged = PageRequest.of(pageNumber, pageSize);
			
			return bookRepository.findByFavoriteUsers_UsernameIn(Arrays.asList(user.getUsername()), paged); 
		}
		
		return null;
	}
}
