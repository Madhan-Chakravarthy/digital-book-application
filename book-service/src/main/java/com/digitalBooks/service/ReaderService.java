package com.digitalBooks.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalBooks.entity.Book;
import com.digitalBooks.entity.Reader;
import com.digitalBooks.repository.Bookrepository;
import com.digitalBooks.repository.ReaderRepository;

@Service
public class ReaderService {
	@Autowired
	Bookrepository bookrepository;
	
	@Autowired
	ReaderRepository readerRepository;
	
	public List<Book> getBooks(String category,String author, Double price,String publisher) {
		List<Book> books = bookrepository
				.findByCategoryIgnoreCaseOrPriceOrPublisherIgnoreCaseOrAuthorAuthorNameIgnoreCase(category, price, publisher, author);
		return books;
	}


	public Optional<Book> getBookById(Integer readerId,Integer bookId) {
		return bookrepository.findById(bookId);
	}


	public Set<Book> getpurchasedBooks(Integer readerId) {
		Optional<Reader> reader =readerRepository.findById(readerId);
		if(reader.isPresent()) {
			return reader.get().getPurchasedBooks();
		}
		return Collections.emptySet();
	}

	public Reader purchaseBook(Integer readerId,Integer bookId) {
		Book book =bookrepository.findById(bookId).get();
		Reader reader = readerRepository.findById(readerId).get();
		Set<Book> purchasedBook= reader.getPurchasedBooks();
		purchasedBook.add(book);
		readerRepository.save(reader);
		return readerRepository.save(reader);
		
	}
}
