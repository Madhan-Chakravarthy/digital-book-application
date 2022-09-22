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
import com.digitalbooks.repository.BookRepository;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.service.impl.AuthorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
	@InjectMocks
	AuthorServiceImpl authorService;

	@Mock
	BookRepository bookrepository;

	@Mock
	AuthorRepository authorRepository;
	
	@Mock
	UserRepository userRepository;
	
	MockData mockdata = new MockData();

	@Test
	void testSaveBook() {
		Book book = mockdata.books.get(0);
		when(userRepository.findById((long)1)).thenReturn(Optional.of(mockdata.users.get(0) ));
		when(bookrepository.save(book)).thenReturn(mockdata.books.get(0));
		assertEquals(authorService.saveBook(mockdata.books.get(0), (long) 1), mockdata.books.get(0));
	}

	@Test
	void testGetAuthorsBooks() {
		when(userRepository.findById((long)1)).thenReturn(Optional.of(mockdata.users.get(0) ));
		when(bookrepository.findByAuthorId(1)).thenReturn(Arrays.asList(mockdata.books.get(0)));
		assertEquals(authorService.getAuthorsBooks((long) 1), Arrays.asList(mockdata.books.get(0)));
	}
	
	@Test
	void testEditBook() {
		when(bookrepository.findById(1)).thenReturn( Optional.of( mockdata.books.get(0)));
		when(bookrepository.save(mockdata.books.get(0))).thenReturn(mockdata.books.get(0));
		assertEquals(authorService.editBook((long) 1,1,mockdata.books.get(0)), mockdata.books.get(0));
		
		when(bookrepository.findById(1)).thenReturn( Optional.empty());
		assertEquals(authorService.editBook((long) 1,1,mockdata.books.get(0)), null);
	}
	
	@Test
	void testSaveAuthor() {
		when(authorRepository.save(mockdata.authors.get(0))).thenReturn(mockdata.authors.get(0));
		assertEquals(authorService.saveAuthor(mockdata.authors.get(0)), mockdata.authors.get(0));
	}

}
