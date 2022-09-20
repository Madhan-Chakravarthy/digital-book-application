package com.digitalbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.entity.Book;
import com.digitalbooks.service.AuthorService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author madhan AuthorController contains the end points to save the book and
 *         get the author's book
 *
 */
@Slf4j
@RestController
@RequestMapping("/author")
@PreAuthorize("hasRole('AUTHOR')")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization","Origin"})
public class AuthorController extends BaseController {

	@Autowired
	AuthorService authorService;
	@Autowired
	RestTemplate restTemplate;

	/**
	 * End point to post the book object to database
	 * 
	 * @param authorId
	 * @param book
	 * @return ResponseEntity
	 */
	@PostMapping("/{authorId}/book")
	public ResponseEntity<Book> saveBook(@PathVariable Integer authorId, @Validated @RequestBody Book book) {
		log.debug("Entering into saveBook");
		Book book1 = authorService.saveBook(book, authorId);
		if (book1 != null) {
			
			/*
			 * HttpHeaders headers = new HttpHeaders();
			 * headers.setContentType(MediaType.APPLICATION_JSON); Author author
			 * =book1.getAuthor(); HttpEntity<Author> requestEntity = new
			 * HttpEntity<>(author, headers);
			 * restTemplate.postForEntity("http://localhost:9000/kafka/email/publish",
			 * requestEntity,Author.class);
			 */
			return new ResponseEntity<Book>(book1,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * End point to search the author's books by id
	 * 
	 * @param id
	 * @return book
	 */
	@GetMapping("/{id}/book")
	public List<Book> getAuthorsBooks(@PathVariable Long id) {
		log.debug("Entering into getAuthorsBooks");
		return authorService.getAuthorsBooks(id);
	}
	
	@GetMapping("/{userId}/books/{bookId}")
	public ResponseEntity<Book> getBookById( @PathVariable Long userId, @PathVariable Integer bookId) {
		log.debug("Entering into getBookById");
		Book book = authorService.getBookById( userId,bookId);
		if (book != null)
			return new ResponseEntity<Book>(book, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
