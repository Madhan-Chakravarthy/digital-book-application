package com.digitalbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.entity.Author;
import com.digitalbooks.entity.Book;
import com.digitalbooks.service.impl.AuthorServiceImpl;

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
@CrossOrigin(origins = "*")
public class AuthorController {

	@Autowired
	AuthorServiceImpl authorService;
	@Autowired
	RestTemplate restTemplate;

	/**
	 * End point to post the book object to database
	 * 
	 * @param authorId
	 * @param book
	 * @return ResponseEntity
	 */
	@PostMapping("/{userId}/book")
	public ResponseEntity<Book> saveBook(@PathVariable Long userId, @Validated @RequestBody Book book) {
		log.debug("Entering into saveBook");
		Book book1 = authorService.saveBook(book, userId);
		if (book1 != null) {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			Author author = book1.getAuthor();
			HttpEntity<Author> requestEntity = new HttpEntity<>(author, headers);
			restTemplate.postForEntity("http://localhost:9000/kafka/email/publish", requestEntity, Author.class);

			return new ResponseEntity<Book>(book1, HttpStatus.CREATED);
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

	/**
	 * End point to edit the book by author
	 * 
	 * @param userId
	 * @param bookId
	 * @param book
	 * @return
	 */
	@PutMapping("/{userId}/book/{bookId}")
	public ResponseEntity<Book> editBook(@PathVariable Long userId, @PathVariable Integer bookId,
			@Validated @RequestBody Book book) {
		log.debug("Entering into saveBook");
		Book book1 = authorService.editBook(userId, bookId, book);
		if (book1 != null) {
			return new ResponseEntity<Book>(book1, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
