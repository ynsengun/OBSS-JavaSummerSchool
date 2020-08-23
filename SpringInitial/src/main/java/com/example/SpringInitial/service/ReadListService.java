package com.example.SpringInitial.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringInitial.entity.Book;
import com.example.SpringInitial.entity.User;
import com.example.SpringInitial.entity.UserBookRead;
import com.example.SpringInitial.model.ListDeleteDTO;
import com.example.SpringInitial.model.UserBookDTO;
import com.example.SpringInitial.repo.BookRepository;
import com.example.SpringInitial.repo.ReadListRepository;
import com.example.SpringInitial.repo.UserRepository;

@Service
public class ReadListService {

	@Autowired
	private ReadListRepository readListRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;

	public Page<UserBookRead> find(long userID, int pageSize, int pageNumber) {
		Optional<User> userById = userRepository.findByIdAndActiveTrue(userID);

		if (userById.isPresent()) {
			User user = userById.get();
			Pageable paged = PageRequest.of(pageNumber, pageSize);

			return readListRepository.findByUser_UsernameIn(Arrays.asList(user.getUsername()), paged);
		}

		return null;
	}

	public UserBookRead save(UserBookDTO dto) {
		Optional<User> userById = userRepository.findByIdAndActiveTrue(dto.getUserID());

		if (userById.isPresent()) {
			Optional<Book> bookById = bookRepository.findByIdAndActiveTrue(dto.getBookID());

			if (bookById.isPresent()) {
				Optional<UserBookRead> byId = readListRepository
						.findByUser_IdInAndBook_IdIn(Arrays.asList(dto.getUserID()), Arrays.asList(dto.getBookID()));

				if (!byId.isPresent()) {

					User user = userById.get();
					Book book = bookById.get();

					UserBookRead entry = new UserBookRead();
					entry.setUser(user);
					entry.setBook(book);
					entry.setDate(new Date());

					return readListRepository.save(entry);
				}
				throw new IllegalArgumentException("Book is already in the list");
			}
			throw new UsernameNotFoundException("Book is not found");
		}
		throw new UsernameNotFoundException("User is not found");
	}

	public void delete(UserBookDTO dto) {
		Optional<User> userById = userRepository.findByIdAndActiveTrue(dto.getUserID());

		if (userById.isPresent()) {
			Optional<Book> bookById = bookRepository.findById(dto.getBookID());

			if (bookById.isPresent()) {
				Optional<UserBookRead> byId = readListRepository
						.findByUser_IdInAndBook_IdIn(Arrays.asList(dto.getUserID()), Arrays.asList(dto.getBookID()));

				if (byId.isPresent()) {
					UserBookRead entry = byId.get();
					readListRepository.delete(entry);
					return;
				}
				throw new IllegalArgumentException("Book is not in the list");
			}
			throw new UsernameNotFoundException("Book is not found");
		}
		throw new UsernameNotFoundException("User is not found");
	}
}
