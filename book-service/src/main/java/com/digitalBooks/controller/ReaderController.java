package com.digitalBooks.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalBooks.entity.Book;
import com.digitalBooks.service.ReaderService;

@RestController
@RequestMapping ("/reader")
public class ReaderController {

	@Autowired
	ReaderService service;
	@GetMapping ("/books")
	public List<Book> getBooks(@RequestParam String category,@RequestParam String author, @RequestParam Double price,@RequestParam String publisher) {
		List<Book> books = service.getBooks(category, author,price,publisher);
		return books;
	}
	@GetMapping ("/books/{id}")
	public Book getBookById(@PathVariable Integer id) {
		return service.getBookById(id).get();
	}
}
