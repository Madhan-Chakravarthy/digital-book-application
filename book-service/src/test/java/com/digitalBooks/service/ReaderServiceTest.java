package com.digitalbooks.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.repository.Bookrepository;
import com.digitalbooks.repository.ReaderRepository;



@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {

	@InjectMocks
	ReaderService readerService;
	@Mock
	Bookrepository bookRepository;
	@Mock
	ReaderRepository readerRepository;
	@Test
	void testGetPurchasedBooks() {
		for(int i=0;i<MockData.readers.size();i++) {
			when(readerRepository.findById(i)).thenReturn(Optional.of(MockData.readers.get(i)));
			assertEquals(readerService.getPurchasedBooks(i), MockData.readers.get(i).getPurchasedBooks());
		}
	}
	@Test
	void testsearchBooks() {
		List<Book> books =MockData.books.stream()
				.filter(book->book.getCategory().equals("COMIC") || book.getAuthor().getAuthorName().equals("Author"))
				.collect(Collectors.toList());
		when(bookRepository.findByCategoryIgnoreCaseOrPriceOrPublisherIgnoreCaseOrAuthorAuthorNameIgnoreCase("COMIC", 100.33, "The Hid", "Author")).thenReturn( books);
		assertEquals(readerService.searchBooks("COMIC", 100.33, "The Hid", "Author"), books);
	}
	@Test
	void testPurchaseBook() {
		Reader reader = MockData.readers.get(0);
		//reader.setPurchasedBooks(MockData.books.subList(0, 4).stream().collect(Collectors.toSet()));
		when(bookRepository.findById(1)).thenReturn( Optional.of(MockData.books.get(0)));
		when( readerRepository.findById(1)).thenReturn(Optional.of( MockData.readers.get(0)));
		when( readerRepository.save(reader)).thenReturn(MockData.readers.get(0));
		System.out.println(readerService.purchaseBook(1, 1));
		assertEquals(readerService.purchaseBook(1, 1),   MockData.readers.get(0));
		
	}
}
