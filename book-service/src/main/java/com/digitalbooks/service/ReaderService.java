package com.digitalbooks.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.enums.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.entity.PaymentRequest;
import com.digitalbooks.entity.User;
import com.digitalbooks.repository.Bookrepository;
import com.digitalbooks.repository.PaymentRepository;
import com.digitalbooks.repository.UserRepository;

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
	/*
	 * public Book getPurchasedBookById(Long readerId, Integer bookId) {
	 * List<Payment> payments = paymentRepository.findByUserId(readerId); Set<Book>
	 * books = new HashSet<Book>(); payments.stream().map(null)
	 * //readerRepository.findById(readerId).get().getPurchasedBooks(); List<Book>
	 * booksById = books.stream().filter(book -> book.getId() ==
	 * bookId).collect(Collectors.toList()); if (!booksById.isEmpty()) return
	 * booksById.get(0); return null; }
	 */

	/**
	 * business logic to get all purchased book by reader
	 * 
	 * @param readerId
	 * @return
	 */
	public Set<Book> getPurchasedBooks(Long userId) {
		List<Payment> payments = paymentRepository.findByUserId(userId);
		Set<Book> books = new HashSet<Book>();
		payments.forEach(payment->{
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
	public Payment purchaseBook( Long userId,PaymentRequest paymentRequest) {	
		User user =userRepository.findById(userId).get();
		Payment payment = new Payment();
		payment.setDate(LocalDate.now());
		payment.setTime(LocalTime.now());
		payment.setUser(user);
		Set<Book> books = new HashSet<Book>();
		paymentRequest.getBookIds().forEach(id->	{
			books.add(bookrepository.findById(id).get());
		});
		payment.setPurchasedBooks(books);
		return paymentRepository.save(payment);

	}

	public Book getBookById(Integer bookId) {
		return bookrepository.findById(bookId).get();
	}

	public List<Book> simpleSearchBooks(String searchParam) {
		 for (BookCategory category :BookCategory.values()) {
		        if (category.name().equalsIgnoreCase(searchParam)) {
		            return bookrepository.findByCategory(category);
		        }
		    }
		 return bookrepository.findByTittleIgnoreCaseOrPublisherIgnoreCaseOrAuthorNameIgnoreCase(searchParam, searchParam, searchParam);
	}
}
