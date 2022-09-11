package com.digitalbooks.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;
import com.digitalbooks.service.ReaderService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author madhan ReaderController class have the end points for Reader to
 *         search the book,buy the book and view the book
 */
@Slf4j
@RestController
@RequestMapping("/reader")
@PreAuthorize("hasRole('READER')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReaderController extends BaseController  {

	@Autowired
	ReaderService readerService;
	@Autowired
	RestTemplate restTemplate;
	/**
	 * End point to search the books from all books
	 * 
	 * @param tittle
	 * @param category
	 * @param price
	 * @param publisher
	 * @param authorName
	 * @return list of books
	 */
	@GetMapping("/books/search")
	@PreAuthorize("hasRole('READER')")
	public List<Book> searchBooks(@RequestParam String tittle, @RequestParam BookCategory category,
			@RequestParam Double price, @RequestParam String publisher, @RequestParam String authorName) {
		List<Book> books = readerService.searchBooks(tittle, category, price, publisher, authorName);
		return books;
	}

	/**
	 * End point to search view all purchased book by reader
	 * 
	 * @param readerId
	 * @return book
	 */
	@GetMapping("/{readerId}/books/buy")
	public Set<Book> getPurchasedBooks(@PathVariable Integer readerId) {
		return readerService.getPurchasedBooks(readerId);
	}

	/**
	 * End point to get the book by id from the purchased list of book by reader
	 * 
	 * @param readerId
	 * @param bookId
	 * @return book
	 */
	@GetMapping("/{readerId}/books/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer readerId, @PathVariable Integer bookId) {
		log.debug("Entering into getBookById");
		Book book = readerService.getBookById(readerId, bookId);
		if (book != null)
			return new ResponseEntity<Book>(book, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * End point to purchase the book by reader
	 * 
	 * @param readerId
	 * @param bookId
	 * @return ResponseEntity
	 */
	@PatchMapping("/{readerId}/books/buy/{bookId}")
	public ResponseEntity<Set<Book>> purchaseBook(@PathVariable Integer readerId, @PathVariable Integer bookId) {
		log.debug("Entering into purchaseBook");
		Reader reader=readerService.purchaseBook(readerId, bookId);
		Set<Book> books = reader.getPurchasedBooks();
		if (books != null && !books.isEmpty()) {
			return new ResponseEntity<Set<Book>>(books, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
