package com.digitalbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.entity.Author;
import com.digitalbooks.entity.Book;
import com.digitalbooks.repository.AuthorRepository;
import com.digitalbooks.repository.Bookrepository;




@Service
public class AuthorService {

	@Autowired
	Bookrepository bookrepository;
	@Autowired
	AuthorRepository authorRepository;
	
	public Book saveBook(Book book, Integer id) {
		book.setAuthor(authorRepository.findById(id).get());
		return bookrepository.save(book);	
	}

	public List<Book> getAuthorsBooks(Integer id) {
		return bookrepository.findByAuthorId(id);
	}

}
