package com.digitalbooks.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;
import com.digitalbooks.repository.Bookrepository;
import com.digitalbooks.repository.ReaderRepository;



@Service
public class ReaderService {
	@Autowired
	Bookrepository bookrepository;
	
	@Autowired
	ReaderRepository readerRepository;
	
	public List<Book> searchBooks(String category, Double price,String publisher,String authorName) {
		List<Book> books = bookrepository
				.findByCategoryIgnoreCaseOrPriceOrPublisherIgnoreCaseOrAuthorAuthorNameIgnoreCase(category, price, publisher, authorName);
		return books;
	}


	public List<Book> getBookById(Integer readerId,Integer bookId) {
		Set<Book>books= readerRepository.findById(readerId).get()
						.getPurchasedBooks();
		System.out.println(books);
		 return 	books.stream()
				 .filter(book->book.getId()==bookId)
				 .collect(Collectors.toList());	 
	}


	public Set<Book> getPurchasedBooks(Integer readerId) {
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
