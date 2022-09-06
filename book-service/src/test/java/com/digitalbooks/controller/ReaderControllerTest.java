package com.digitalbooks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.service.ReaderService;

@ExtendWith(MockitoExtension.class)
public class ReaderControllerTest {

	@InjectMocks
	ReaderController readerController;
	@Mock
	ReaderService readerService;

	MockData mockdata = new MockData();
	@Test
	void testPurchaseBook() {
		Reader reader = mockdata.readers.get(0);
		reader.setPurchasedBooks(mockdata.books.subList(0, 1).stream().collect(Collectors.toSet()));
		when(readerService.purchaseBook(1, 1)).thenReturn(mockdata.readers.get(0));
		assertEquals(readerController.purchaseBook(1, 1),
				new ResponseEntity<Set<Book>>(reader.getPurchasedBooks(), HttpStatus.OK));
		reader.setPurchasedBooks(null);
		when(readerService.purchaseBook(4, 5)).thenReturn(reader);
		assertEquals(readerController.purchaseBook(4, 5), new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@Test
	void testGetPurchasedBooks() {
		for (int i = 0; i < mockdata.readers.size(); i++) {
			when(readerService.getPurchasedBooks(i)).thenReturn(mockdata.readers.get(i).getPurchasedBooks());
			assertEquals(readerController.getPurchasedBooks(i), mockdata.readers.get(i).getPurchasedBooks());
		}
	}

	@Test
	void testGetBookById() {
		when(readerService.getBookById(1, 2)).thenReturn(mockdata.books.get(1));
		assertEquals(readerController.getBookById(1, 2),
				new ResponseEntity<Book>(mockdata.books.get(1), HttpStatus.OK));
		when(readerService.getBookById(1, 5)).thenReturn(null);
		assertEquals(readerController.getBookById(1, 5), new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Test
	void testsearchBooks() {
		List<Book> books = mockdata.books.stream().filter(book -> book.getCategory().equals("COMIC"))
				.collect(Collectors.toList());
		when(readerService.searchBooks("tittle",BookCategory.COMIC, 100.39, "The Hidu", "AuthorA")).thenReturn(books);
		assertEquals(readerController.searchBooks("tittle",BookCategory.COMIC, 100.39, "The Hidu", "AuthorA"), books);
	}
}
