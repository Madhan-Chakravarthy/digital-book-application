package com.digitalBooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalBooks.entity.Book;
import com.digitalBooks.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController extends BaseController {

	@Autowired
	AuthorService authorService;
	@PostMapping
	public Integer saveBook(@Validated @RequestBody Book book) {
		 authorService.saveBook(book);
		 return book.getId();
	}
}
