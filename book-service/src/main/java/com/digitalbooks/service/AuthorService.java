package com.digitalbooks.service;

import java.util.List;

import com.digitalbooks.entity.Author;
import com.digitalbooks.entity.Book;

public interface AuthorService {
	public Book saveBook(Book book, Long id);
	public List<Book> getAuthorsBooks(Long userId);
	public Author saveAuthor(Author author);
	public Book editBook(Long userId, Integer bookId, Book book);
}
