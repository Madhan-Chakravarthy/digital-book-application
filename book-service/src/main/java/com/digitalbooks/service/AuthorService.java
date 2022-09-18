package com.digitalbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Author;
import com.digitalbooks.repository.AuthorRepository;
import com.digitalbooks.repository.Bookrepository;

/**
 * 
 * @author madhan AuthorService class handles all the business logic need for
 *         Author controller
 *
 */

@Service
public class AuthorService {

	@Autowired
	Bookrepository bookrepository;
	@Autowired
	AuthorRepository authorRepository;

	/**
	 * business logic to save the book
	 * 
	 * @param book
	 * @param id
	 * @return
	 */
	public Book saveBook(Book book, Integer id) {
		book.setAuthor(authorRepository.findById(id).get());
		return bookrepository.save(book);
	}

	/**
	 * business logic to get author's book
	 * 
	 * @param id
	 * @return
	 */
	public List<Book> getAuthorsBooks(Integer id) {
		return bookrepository.findByAuthorId(id);
	}

	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
		
	}
}
