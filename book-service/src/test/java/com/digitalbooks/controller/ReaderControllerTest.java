package com.digitalbooks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
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
import com.digitalbooks.entity.PaymentRequest;
import com.digitalbooks.entity.ResponseMessage;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.service.impl.ReaderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReaderControllerTest {

	@InjectMocks
	ReaderController readerController;
	@Mock
	ReaderServiceImpl readerService;

	MockData mockdata = new MockData();

	@Test
	void testPurchaseBook() {
		PaymentRequest paymentRequest = new PaymentRequest();
		Set<Integer> bookIds = new HashSet<Integer>();
		bookIds.add(1);
		bookIds.add(2);
		bookIds.add(3);
		paymentRequest.setBookIds(bookIds);
		when(readerService.purchaseBook((long) 2, paymentRequest)).thenReturn(mockdata.payments.get(0));
		assertEquals(readerController.purchaseBook((long) 2, paymentRequest),
				 new ResponseEntity<ResponseMessage>(new ResponseMessage("successfully purchased"), HttpStatus.CREATED));

		when(readerService.purchaseBook((long) 2, paymentRequest)).thenReturn(null);
		assertEquals(readerController.purchaseBook((long) 2, paymentRequest), new ResponseEntity<>(HttpStatus.BAD_REQUEST));

	}

	@Test
	void testGetPurchasedBooks() {
		when(readerService.getPurchasedBooks((long) 2)).thenReturn(new HashSet<Book>(mockdata.books));
		assertEquals(readerController.getPurchasedBooks((long) 2), new HashSet<Book>(mockdata.books));
	}

	@Test
	void testGetBookById() {
		when(readerService.getBookById((long) 1, 1)).thenReturn(mockdata.books.get(1));
		assertEquals(readerController.getBookById((long) 1, 1),
				new ResponseEntity<Book>(mockdata.books.get(1), HttpStatus.OK));

		when(readerService.getBookById((long) 1, 5)).thenReturn(null);
		assertEquals(readerController.getBookById((long) 1, 5), new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@Test
	void testSearchBooks() {
		List<Book> books = mockdata.books.stream().filter(book -> book.getCategory().equals("COMIC"))
				.collect(Collectors.toList());
		when(readerService.searchBooks("tittle", BookCategory.COMIC, 100.39, "The Hidu", "AuthorA")).thenReturn(books);
		assertEquals(readerController.searchBooks("tittle", BookCategory.COMIC, 100.39, "The Hidu", "AuthorA"), books);
	}

	@Test
	void testSimpleSearchBooks() {
		// List<Book> books = mockdata.books.stream().filter(book ->
		// book.getCategory().equals("COMIC"))
		// .collect(Collectors.toList());
		when(readerService.simpleSearchBooks("COMIC")).thenReturn(mockdata.books);
		assertEquals(readerController.simpleSearchBooks("COMIC"), mockdata.books);
	}
}
