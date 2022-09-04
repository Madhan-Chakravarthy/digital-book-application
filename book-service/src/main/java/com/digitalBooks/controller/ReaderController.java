package com.digitalbooks.controller;

import java.util.List;
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

import com.digitalbooks.entity.Book;
import com.digitalbooks.service.ReaderService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping ("/reader")
public class ReaderController extends BaseController{

	@Autowired
	ReaderService readerService;
	@GetMapping ("/books/search")
	public List<Book> searchBooks(@RequestParam String category,@RequestParam Double price,@RequestParam String publisher,@RequestParam String authorName) {
		List<Book> books = readerService.searchBooks(category, price, publisher, authorName);
		return books;
	}
	@GetMapping ("/{readerId}/books/buy")
	public Set<Book> getPurchasedBooks(@PathVariable Integer readerId) {
		return readerService.getPurchasedBooks(readerId);
	}
	@GetMapping ("/{readerId}/books/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer readerId,@PathVariable Integer bookId) {
		  List<Book> books=	readerService.getBookById(readerId,bookId);
		 System.out.println(books);
		  if(!books.isEmpty() && books.get(0)!=null)
			return  new ResponseEntity<Book>(books.get(0),HttpStatus.OK);	 
		  else
			  return new ResponseEntity<> (HttpStatus.NOT_FOUND); 
	}
	@PatchMapping ("/{readerId}/books/buy/{bookId}")
	  public ResponseEntity<Set<Book>> purchaseBook(@PathVariable Integer readerId,@PathVariable Integer bookId){
		Set<Book> books =readerService.purchaseBook(readerId, bookId).getPurchasedBooks();
		 if( books !=null && !books.isEmpty())
			 return new ResponseEntity<Set<Book>> (books,HttpStatus.OK);
		  
		  return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	  }

		
}
