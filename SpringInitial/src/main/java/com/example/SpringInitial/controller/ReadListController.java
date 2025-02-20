package com.example.SpringInitial.controller;

import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringInitial.entity.UserBookFavorite;
import com.example.SpringInitial.entity.UserBookRead;
import com.example.SpringInitial.model.ListDeleteDTO;
import com.example.SpringInitial.model.UserBookDTO;
import com.example.SpringInitial.service.ReadListService;

@RestController
@RequestMapping("/api/read-list")
public class ReadListController {
	
	private ReadListService readListService;

	@Autowired
	public ReadListController(ReadListService readListService) {
		this.readListService = readListService;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@PreAuthorize("#id == authentication.principal.id")
	public ResponseEntity<?> get(@PathVariable long id,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
		Page<UserBookRead> books = readListService.find(id, pageSize, pageNumber);

		return ResponseEntity.ok(books);
	}
	
	@GetMapping("")
	@ResponseBody
	@PreAuthorize("#userID == authentication.principal.id")
	public ResponseEntity<?> getExistence(@RequestParam long userID, @RequestParam long bookID) {
		UserBookDTO dto = new UserBookDTO();
		dto.setUserID(userID);
		dto.setBookID(bookID);
		Optional<UserBookRead> relation = readListService.findExistence(dto);
		
		if(relation.isPresent()) {
			Map<String, Boolean> resultMap = new HashMap<>();
			resultMap.put("exist",true);
			return ResponseEntity.ok(resultMap);
		}
		Map<String, Boolean> resultMap = new HashMap<>();
		resultMap.put("exist",false);
		return ResponseEntity.ok(resultMap);
	}
	
	@PostMapping("")
	@ResponseBody
	@PreAuthorize("#dto.userID == authentication.principal.id")
	public ResponseEntity<?> post(@Valid @RequestBody UserBookDTO dto) {
		UserBookRead relation = readListService.save(dto);

		return ResponseEntity.ok(relation);
	}
	
	@DeleteMapping("")
	@ResponseBody
	@PreAuthorize("#dto.userID == authentication.principal.id")
	public ResponseEntity<?> delete(@Valid @RequestBody UserBookDTO dto) {
		readListService.delete(dto);

		return ResponseEntity.ok(true);
	}
}
