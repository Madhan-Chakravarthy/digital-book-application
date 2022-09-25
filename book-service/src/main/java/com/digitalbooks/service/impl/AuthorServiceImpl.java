package com.digitalbooks.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Author;
import com.digitalbooks.repository.AuthorRepository;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.service.AuthorService;

/**
 * 
 * @author madhan AuthorService class handles all the business logic need for
 *         Author controller
 *
 */

@Service
public class AuthorServiceImpl  implements AuthorService{

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
	public Book saveBook(Book book, Long id) {
		Author author = userRepository.findById(id).get().getAuthor();
		book.setAuthor(author);
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
	/**
	 * service method to save the author
	 */
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
		
	}

	/**
	 * business logic to edit author's book
	 */
	public Book editBook(Long userId, Integer bookId, Book book) {
		Optional<Book> originalBook =bookrepository.findById(bookId);
		if(originalBook.isPresent()) {
			originalBook.get().setActive(book.getActive());
			originalBook.get().setCategory(book.getCategory());
			originalBook.get().setContent(book.getContent());
			originalBook.get().setPrice(book.getPrice());
			originalBook.get().setPublishDate(book.getPublishDate());
			originalBook.get().setPublisher(book.getPublisher());
			originalBook.get().setTittle(book.getTittle());
			return bookrepository.save(originalBook.get());
		}
		return null;
	}
}
