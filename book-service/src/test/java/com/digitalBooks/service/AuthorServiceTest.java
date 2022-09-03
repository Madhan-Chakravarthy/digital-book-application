package com.digitalbooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalbooks.entity.Book;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.repository.AuthorRepository;
import com.digitalbooks.repository.Bookrepository;



@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
	@InjectMocks
	AuthorService authorService;
	
	@Mock
	 Bookrepository bookrepository;
	
	@Mock
	AuthorRepository authorRepository;

	@Test
	void testSaveBook() {
		Book book = MockData.books.get(0);
		when(authorRepository.findById(1)).thenReturn(Optional.of(MockData.authors.get(0)));
		when(bookrepository.save(book)).thenReturn(MockData.books.get(0));
		assertEquals(authorService.saveBook(MockData.books.get(0),1 ), MockData.books.get(0));
	}
	@Test
	void testGetAuthorsBooks() {
		when(bookrepository.findByAuthorId(5)).thenReturn( Arrays.asList(MockData.books.get(4)));
		assertEquals(authorService.getAuthorsBooks(5), Arrays.asList(MockData.books.get(4)) );
	}
	

}
