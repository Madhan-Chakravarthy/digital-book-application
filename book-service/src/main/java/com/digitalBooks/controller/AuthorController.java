package com.digitalBooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalBooks.entity.Author;
import com.digitalBooks.entity.Book;
import com.digitalBooks.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController extends BaseController {

	@Autowired
	AuthorService authorService;
	@PostMapping("/{authorId}/book")
	public Integer saveBook(@PathVariable Integer authorId ,@Validated @RequestBody Book book) {
		 authorService.saveBook(book,authorId);
		 return book.getId();
	}
	
	@GetMapping ("/{autherName}")
	public List<Author> getBookById(@PathVariable String autherName) {
		return authorService.getAuthorByName(autherName);
	}
	
	@GetMapping ("/{id}/book")
	public List<Book> getAuthorsBooks(@PathVariable Integer id) {
		return authorService.getAuthorsBooks(id);
	} 
}
