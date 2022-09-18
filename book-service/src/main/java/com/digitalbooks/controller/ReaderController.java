package com.digitalbooks.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.entity.PaymentRequest;
import com.digitalbooks.entity.ResponseMessage;
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
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReaderController  extends BaseController{

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
	public List<Book> searchBooks(@RequestParam String tittle, @RequestParam BookCategory category,
			@RequestParam Double price, @RequestParam String publisher, @RequestParam String authorName) {
		List<Book> books = readerService.searchBooks(tittle, category, price, publisher, authorName);
		return books;
	}
	
	@GetMapping("/books/ssearch")
	public List<Book> simpleSearchBooks(@RequestParam String searchParam) {
		log.debug(searchParam);
		List<Book> books = readerService.simpleSearchBooks(searchParam);
		return books;
	}
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Book> getBookById( @PathVariable Integer bookId) {
		log.debug("Entering into getBookById");
		Book book = readerService.getBookById( bookId);
		if (book != null)
			return new ResponseEntity<Book>(book, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	/**
	 * End point to search view all purchased book by reader
	 * 
	 * @param readerId
	 * @return book
	 */
	@GetMapping("/{userId}/books/buy")
	public Set<Book> getPurchasedBooks(@PathVariable Long userId) {
		return readerService.getPurchasedBooks(userId);
	}

	/**
	 * End point to get the book by id from the purchased list of book by reader
	 * 
	 * @param readerId
	 * @param bookId
	 * @return book
	 */
	/*
	 * @GetMapping("/{userId}/books/{bookId}") public ResponseEntity<Book>
	 * getPurchasedBookById(@PathVariable Integer userId, @PathVariable Integer
	 * bookId) { log.debug("Entering into getBookById"); Book book =
	 * readerService.getPurchasedBookById(userId, bookId); if (book != null) return
	 * new ResponseEntity<Book>(book, HttpStatus.OK);
	 * 
	 * return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 */
	/**
	 * End point to purchase the book by reader
	 * 
	 * @param readerId
	 * @param bookId
	 * @return ResponseEntity
	 */
	@PostMapping("/{userId}/books/buy/")
	public ResponseEntity<?> purchaseBook(@PathVariable Long userId,@RequestBody PaymentRequest paymentRequest) {
		log.debug("Entering into purchaseBook");
		Payment payment=readerService.purchaseBook(userId,paymentRequest);
		if (payment!=null) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("successfully purchased"), HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
