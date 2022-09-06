package com.digitalbooks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.logging.Handler;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.digitalbooks.entity.Book;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.service.AuthorService;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {
	@InjectMocks
	AuthorController authorController;

	@Mock
	AuthorService authorService;
	MockData mockdata = new MockData();
	@Test
	public void testSaveBook() {
		when(authorService.saveBook(mockdata.books.get(1), 1)).thenReturn(mockdata.books.get(1));
		assertEquals(authorController.saveBook(1, mockdata.books.get(1)),
				new ResponseEntity<Book>(mockdata.books.get(1), HttpStatus.CREATED));
		when(authorService.saveBook(null, 1)).thenReturn(null);
		assertEquals(authorController.saveBook(1, null), new ResponseEntity<>(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void testGetAuthorsBooks() {
		List<Book> books = mockdata.books.stream().filter(book -> book.getAuthor().getId() == 1)
				.collect(Collectors.toList());
		when(authorService.getAuthorsBooks(1)).thenReturn(books);
		assertEquals(authorController.getAuthorsBooks(1), books);
	}

}
