package com.digitalbooks.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.entity.PaymentRequest;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.repository.PaymentRepository;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.service.impl.ReaderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReaderServiceTest {

	@InjectMocks
	ReaderServiceImpl readerService;
	@Mock
	BookRepository bookRepository;
	@Mock
	UserRepository userRepository;

	@Mock
	PaymentRepository paymentRepository;

	MockData mockdata = new MockData();

	@Test
	void testGetPurchasedBooks() {
		when(paymentRepository.findByUserId((long) 1)).thenReturn(mockdata.payments);
		assertEquals(readerService.getPurchasedBooks((long) 1), mockdata.payments.get(0).getPurchasedBooks());

		when(paymentRepository.findByUserId((long) 1)).thenReturn(Arrays.asList(mockdata.payments.get(1)));
		assertEquals(readerService.getPurchasedBooks((long) 1), Collections.emptySet());

	}

	@Test
	void testsearchBooks() {
		List<Book> books = mockdata.books.stream()
				.filter(book -> book.getCategory().equals("COMIC") || book.getAuthor().getName().equals("Author"))
				.collect(Collectors.toList());
		when(bookRepository.findByTittleIgnoreCaseOrCategoryOrPriceOrPublisherIgnoreCaseOrAuthorNameIgnoreCase("tittle",
				BookCategory.COMIC, 100.33, "The Hid", "Author")).thenReturn(books);
		assertEquals(readerService.searchBooks("tittle", BookCategory.COMIC, 100.33, "The Hid", "Author"), books);
	}

	@Test
	void testPurchaseBook() {
		PaymentRequest paymentRequest = new PaymentRequest();
		Set<Integer> bookIds = new HashSet<Integer>();
		bookIds.add(1);
		bookIds.add(2);
		bookIds.add(3);
		paymentRequest.setBookIds(bookIds);
		when(userRepository.findById((long) 1)).thenReturn(Optional.of(mockdata.users.get(0)));
		when(bookRepository.findById(1)).thenReturn(Optional.of(mockdata.books.get(0)));
		when(bookRepository.findById(2)).thenReturn(Optional.of(mockdata.books.get(1)));
		when(bookRepository.findById(3)).thenReturn(Optional.of(mockdata.books.get(2)));
		when(paymentRepository.save(mockdata.payments.get(0))).thenReturn(mockdata.payments.get(0));
		assertEquals(readerService.purchaseBook((long) 1, paymentRequest), mockdata.payments.get(0));
	}

	@Test
	void testGetBookById() {
		when(paymentRepository.findByUserId((long) 1)).thenReturn(mockdata.payments);
		when(bookRepository.findById(1)).thenReturn(Optional.of(mockdata.books.get(0)));
		assertEquals(readerService.getBookById((long)1, 1), mockdata.books.get(0));
		when(paymentRepository.findByUserId((long) 2)).thenReturn(Collections.EMPTY_LIST);
		//mockdata.books.get(0).setPurchased(null);
		assertEquals(readerService.getBookById((long)2, 1), mockdata.books.get(0));
	}

	@Test
	void testSimpleSearchBooks() {
		when(bookRepository.findByCategory(BookCategory.COMIC)).thenReturn(mockdata.books);

		assertEquals(readerService.simpleSearchBooks("comic"), mockdata.books);
		when(bookRepository.findByTittleIgnoreCaseOrPublisherIgnoreCaseOrAuthorNameIgnoreCase("Author1", "Author1",
				"Author1")).thenReturn(Arrays.asList(mockdata.books.get(0)));
		assertEquals(readerService.simpleSearchBooks("Author1"), Arrays.asList(mockdata.books.get(0)));

	}

}
