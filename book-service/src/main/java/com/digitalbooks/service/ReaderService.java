package com.digitalbooks.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;
import com.digitalbooks.repository.Bookrepository;
import com.digitalbooks.repository.ReaderRepository;

/**
 * 
 * @author madhan ReaderService class handles all the business logic need for
 *         Reader controller
 *
 */
@Service
public class ReaderService {
	@Autowired
	Bookrepository bookrepository;

	@Autowired
	ReaderRepository readerRepository;

	/**
	 * business logic to search the book from all list of book
	 * 
	 * @param tittle
	 * @param category
	 * @param price
	 * @param publisher
	 * @param name
	 * @return list of book
	 */
	public List<Book> searchBooks(String tittle, BookCategory category, Double price, String publisher, String name) {
		List<Book> books = bookrepository
				.findByTittleIgnoreCaseOrCategoryOrPriceOrPublisherIgnoreCaseOrAuthorNameIgnoreCase(tittle, category,
						price, publisher, name);
		return books;
	}

	/**
	 * business logic to get book by in purchased list of book
	 * 
	 * @param readerId
	 * @param bookId
	 * @return book
	 */
	public Book getBookById(Integer readerId, Integer bookId) {
		Set<Book> books = readerRepository.findById(readerId).get().getPurchasedBooks();
		List<Book> booksById = books.stream().filter(book -> book.getId() == bookId).collect(Collectors.toList());
		if (!booksById.isEmpty())
			return booksById.get(0);
		return null;
	}

	/**
	 * business logic to get all purchased book by reader
	 * 
	 * @param readerId
	 * @return
	 */
	public Set<Book> getPurchasedBooks(Integer readerId) {
		Optional<Reader> reader = readerRepository.findById(readerId);
		if (reader.isPresent()) {
			return reader.get().getPurchasedBooks();
		}
		return Collections.emptySet();
	}

	/**
	 * Business logic to purchase book by reader
	 * 
	 * @param readerId
	 * @param bookId
	 * @return
	 */
	public Reader purchaseBook(Integer readerId, Integer bookId) {
		Book book = bookrepository.findById(bookId).get();
		Reader reader = readerRepository.findById(readerId).get();
		Set<Book> purchasedBook = reader.getPurchasedBooks();
		purchasedBook.add(book);
		readerRepository.save(reader);
		return readerRepository.save(reader);

	}
}
