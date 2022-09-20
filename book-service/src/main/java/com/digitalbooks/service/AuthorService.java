package com.digitalbooks.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Author;
import com.digitalbooks.repository.AuthorRepository;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.repository.UserRepository;

/**
 * 
 * @author madhan AuthorService class handles all the business logic need for
 *         Author controller
 *
 */

@Service
public class AuthorService {

	@Autowired
	BookRepository bookrepository;
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	UserRepository userRepository;

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
	public List<Book> getAuthorsBooks(Long userId) {
		Author author = userRepository.findById(userId).get().getAuthor();
		return bookrepository.findByAuthorId(author.getId());
	}

	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
		
	}

	public Book getBookById(Long userId, Integer bookId) {
		Author author = userRepository.findById(userId).get().getAuthor();
		List<Book> books=bookrepository.findByAuthorId(author.getId()).stream()
		.filter(book->book.getId()==bookId).collect(Collectors.toList());
		
		if(!books.isEmpty()) {
			books.get(0).setAuthorsBook(true);
			return books.get(0);
		}
		
		return null;
	}
}
