package com.digitalbooks.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.entity.PaymentRequest;
import com.digitalbooks.entity.User;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.repository.PaymentRepository;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.service.ReaderService;

/**
 * 
 * @author madhan ReaderService class handles all the business logic need for
 *         Reader controller
 *
 */
@Service
public class ReaderServiceImpl implements ReaderService {
	@Autowired
	BookRepository bookrepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	UserRepository userRepository;

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
						price, publisher, name)
				.stream().filter(book -> book.getActive() == true).collect(Collectors.toList());
		return books;
	}

	/**
	 * business logic to get all purchased book by reader
	 * 
	 * @param readerId
	 * @return
	 */
	public Set<Book> getPurchasedBooks(Long userId) {
		List<Payment> payments = paymentRepository.findByUserId(userId);
		Set<Book> books = new HashSet<Book>();
		payments.forEach(payment -> {
			books.addAll(payment.getPurchasedBooks());
		});
		if (!books.isEmpty()) {
			return books;
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
	public Payment purchaseBook(Long userId, PaymentRequest paymentRequest) {
		User user = userRepository.findById(userId).get();
		Payment payment = new Payment();
		payment.setDate(LocalDate.now());
		payment.setUser(user);
		Set<Book> books = new HashSet<Book>();
		paymentRequest.getBookIds().forEach(id -> {
			books.add(bookrepository.findById(id).get());
		});
		payment.setPurchasedBooks(books);
		return paymentRepository.save(payment);

	}

	/**
	 * Business logic to get book by id
	 */
	public Book getBookById(Long userId, Integer bookId) {
		List<Integer> bookIds = paymentRepository.findByUserId(userId).stream().map(Payment::getPurchasedBooks)
				.flatMap(Set::stream).map(Book::getId).collect(Collectors.toList());
		Book book = bookrepository.findById(bookId).get();
		if (bookIds.contains(bookId))
			book.setPurchased(true);
		else
			book.setPurchased(false);
		return book;
	}
	
	/**
	 * Business logic to search with one word 
	 */
	public List<Book> simpleSearchBooks(String searchParam) {
		for (BookCategory category : BookCategory.values()) {
			if (category.name().equalsIgnoreCase(searchParam)) {
				return bookrepository.findByCategory(category).stream().filter(book -> book.getActive() == true)
						.collect(Collectors.toList());
			}
		}
		return bookrepository.findByTittleIgnoreCaseOrPublisherIgnoreCaseOrAuthorNameIgnoreCase(searchParam,
				searchParam, searchParam).stream().filter(book -> book.getActive() == true)
				.collect(Collectors.toList());
	}
}
