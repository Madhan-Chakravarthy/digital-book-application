package com.digitalbooks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.service.ReaderService;



@ExtendWith(MockitoExtension.class)
class ReaderControllerTest {

	@InjectMocks
	ReaderController readerController;
	@Mock
	ReaderService readerService;

	@Test
	void testGetPurchasedBooks() {
		for(int i=0;i<MockData.readers.size();i++) {
			when(readerService.getPurchasedBooks(i)).thenReturn(MockData.readers.get(i).getPurchasedBooks());
			assertEquals(readerController.getPurchasedBooks(i), MockData.readers.get(i).getPurchasedBooks());
		}
	}
	@Test
	void testGetBookById() {
		when(readerService.getBookById(1,2)).thenReturn( Optional.of( MockData.books.get(1)));
		assertEquals(readerController.getBookById(1,2),   new ResponseEntity<Book>(MockData.books.get(1),HttpStatus.OK));
	}
	@Test
	void testsearchBooks() {
		List<Book> books =MockData.books.stream()
				.filter(book->book.getCategory().equals("COMIC"))
				.collect(Collectors.toList());
		when(readerService.searchBooks("COMIC", 100.39, "The Hidu", "AuthorA")).thenReturn(  books);
		assertEquals(readerController.searchBooks("COMIC", 100.39, "The Hidu", "AuthorA"), books);
	}
	@Test
	void testPurchaseBook() {
		Reader reader = MockData.readers.get(0);
		reader.setPurchasedBooks(MockData.books.subList(0, 4).stream().collect(Collectors.toSet()));
		when(readerService.purchaseBook(1, 4)).thenReturn( reader);
		assertEquals(readerController.purchaseBook(1, 4),   new ResponseEntity<Set<Book>>(reader.getPurchasedBooks(),HttpStatus.OK));
	}
}
