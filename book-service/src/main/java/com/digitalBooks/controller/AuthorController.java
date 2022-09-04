package com.digitalbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.entity.Book;
import com.digitalbooks.service.AuthorService;



@RestController
@RequestMapping("/author")
public class AuthorController extends BaseController {

	@Autowired
	AuthorService authorService;
	@PostMapping("/{authorId}/book")
	public Book saveBook(@PathVariable Integer authorId ,@Validated @RequestBody Book book) {
		 authorService.saveBook(book,authorId);
		 return authorService.saveBook(book,authorId);
	}
	
	@GetMapping ("/{id}/book")
	public List<Book> getAuthorsBooks(@PathVariable Integer id) {
		return authorService.getAuthorsBooks(id);
	} 
}
