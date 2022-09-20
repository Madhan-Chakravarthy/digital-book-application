package com.digitalbooks.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.repository.ReaderRepository;



@ExtendWith(MockitoExtension.class)
public class ReaderServiceTest {

	@InjectMocks
	ReaderService readerService;
	@Mock
	BookRepository bookRepository;
	@Mock
	ReaderRepository readerRepository;
	
	MockData mockdata = new MockData();
	
	@Test
	void testGetPurchasedBooks() {
			when(readerRepository.findById(1)).thenReturn(Optional.of(mockdata.readers.get(1)));
			assertEquals(readerService.getPurchasedBooks(1), mockdata.readers.get(1).getPurchasedBooks());	

		when(readerRepository.findById(5)).thenReturn(Optional.empty());
		assertEquals(readerService.getPurchasedBooks(5), Collections.emptySet());
	}
	@Test
	void testsearchBooks() {
		List<Book> books =mockdata.books.stream()
				.filter(book->book.getCategory().equals("COMIC") || book.getAuthor().getName().equals("Author"))
				.collect(Collectors.toList());
		when(bookRepository.findByTittleIgnoreCaseOrCategoryOrPriceOrPublisherIgnoreCaseOrAuthorNameIgnoreCase("tittle",BookCategory.COMIC, 100.33, "The Hid", "Author")).thenReturn( books);
		assertEquals(readerService.searchBooks("tittle",BookCategory.COMIC, 100.33, "The Hid", "Author"), books);
	}
	@Test
	void testPurchaseBook() {
		Reader reader = mockdata.readers.get(0);
		when(bookRepository.findById(1)).thenReturn( Optional.of(mockdata.books.get(0)));
		when( readerRepository.findById(1)).thenReturn(Optional.of( mockdata.readers.get(0)));
		when( readerRepository.save(reader)).thenReturn(mockdata.readers.get(0));
		assertEquals(readerService.purchaseBook(1, 1),   mockdata.readers.get(0));
	}
	@Test
	void testGetBookById() {
		when(readerRepository.findById(1)).thenReturn( Optional.of(mockdata.readers.get(0)));
		assertEquals(readerService.getPurchasedBookById(1, 1), mockdata.books.get(0));
		mockdata.readers.get(0).setPurchasedBooks(Collections.emptySet());
		when(readerRepository.findById(1)).thenReturn( Optional.of(mockdata.readers.get(0)));
		assertEquals(readerService.getPurchasedBookById(1, 1), null);
		mockdata.readers.get(0).setPurchasedBooks(mockdata.books.stream().collect(Collectors.toSet()));
	}
	
}
