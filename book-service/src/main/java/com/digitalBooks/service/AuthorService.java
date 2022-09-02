package com.digitalBooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalBooks.entity.Author;
import com.digitalBooks.entity.Book;
import com.digitalBooks.repository.AuthorRepository;
import com.digitalBooks.repository.Bookrepository;


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
	
	public List<Author> getAuthorByName(String autherName) {
		return authorRepository.findByAuthorName(autherName);
	}
	
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}

	public List<Book> getAuthorsBooks(Integer id) {
		System.out.println(bookrepository.findByAuthorId(id).toString());
		return bookrepository.findByAuthorId(id);
	}

}
