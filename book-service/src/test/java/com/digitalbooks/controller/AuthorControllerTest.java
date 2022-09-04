package com.digitalbooks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalbooks.entity.Book;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.service.AuthorService;



@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {
	@InjectMocks
	AuthorController authorController;
	
	@Mock
	AuthorService authorService;
	@Test
	void testSaveBook() {
		when(authorService.saveBook(MockData.books.get(1), 1)).thenReturn(MockData.books.get(1));
		assertEquals(authorController.saveBook(1,MockData.books.get(1) ), MockData.books.get(1));
	}
	
	@Test
	void testGetAuthorsBooks() {
        List<Book> books =MockData.books.stream().filter(book->book.getAuthor().getId()==5).collect(Collectors.toList());
		when(authorService.getAuthorsBooks(5)).thenReturn(books);
		assertEquals(authorController.getAuthorsBooks(5) , books);
	}

}
