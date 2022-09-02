package com.digitalBooks.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalBooks.entity.Book;
import com.digitalBooks.service.ReaderService;

@RestController
@RequestMapping ("/reader")
public class ReaderController extends BaseController{

	@Autowired
	ReaderService readerService;
	@GetMapping ("/books/search")
	public List<Book> getBooks(@RequestParam String category,@RequestParam String author, @RequestParam Double price,@RequestParam String publisher) {
		List<Book> books = readerService.getBooks(category, author,price,publisher);
		return books;
	}
	@GetMapping ("/{readerId}/books/buy")
	public Set<Book> getPurchasedBooks(@PathVariable Integer readerId) {
		return readerService.getpurchasedBooks(readerId);
	}
	@GetMapping ("/{readerId}/books/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer readerId,@PathVariable Integer bookId) {
		  Optional<Book> book=	readerService.getBookById(readerId,bookId);
		  if(book.isPresent()) 
			  return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		  
			return  new ResponseEntity<Book>(book.get(),HttpStatus.OK);	  
	}
	@PatchMapping ("/{readerId}/books/buy/{bookId}")
	  public ResponseEntity<Book> purchaseBook(@PathVariable Integer readerId,@PathVariable Integer bookId){
		 if( readerService.purchaseBook(readerId, bookId) !=null)
			 return new ResponseEntity<> (HttpStatus.OK);
		  
		  return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	  }

		
}
