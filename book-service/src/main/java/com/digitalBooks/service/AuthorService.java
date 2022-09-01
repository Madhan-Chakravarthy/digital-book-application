package com.digitalBooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalBooks.entity.Book;
import com.digitalBooks.repository.Bookrepository;


@Service
public class AuthorService {

	@Autowired
	Bookrepository bookrepository;
	public Book saveBook(Book book) {
		System.out.println(book.getPublisher());
		return bookrepository.save(book);
		
	}
}
