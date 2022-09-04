package com.digitalbooks.mockData;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.digitalbooks.entity.Author;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;



public class MockData {
	 
   public static List<Author> authors =Arrays.asList(
		   new Author(1, "AuthorA", "abc@gmail.com"),
		   new Author(2, "AuthorB", "cde@gmail.com"),
		   new Author(3, "AuthorC", "fgh@gmail.com"),
		   new Author(4, "AuthorD", "ijk@gmail.com"),
		   new Author(5, "AuthorE", "ijk@gmail.com"),
		   new Author(6, "AuthorF", "ijk@gmail.com"),
		   new Author(7, "AuthorG", "ijk@gmail.com"),
		   new Author(8, "AuthorH", "ijk@gmail.com"),
		   new Author(9, "AuthorI", "ijk@gmail.com"));
   
	 public static List<Book> books = Arrays.asList(
			 new Book(1, authors.get(0), "COMIC", "https://www.gettyimages.com/photos/book", 100.20, LocalDate.now(), "The Hindu", true, "hiiii"),
			 new Book(1, authors.get(1), "Romantic", "https://www.gettyimages.com/photos/book", 100.20, LocalDate.now(), "The Hindu", true, "hiiii"),
			 new Book(1, authors.get(2), "Thriller", "https://www.gettyimages.com/photos/book", 100.20, LocalDate.now(), "The Hindu", true, "hiiii"),
			 new Book(1, authors.get(3), "COMIC", "https://www.gettyimages.com/photos/book", 100.20, LocalDate.now(), "The Hindu", true, "hiiii"),
			 new Book(1, authors.get(4), "Crime", "https://www.gettyimages.com/photos/book", 100.20, LocalDate.now(), "The Hindu", true, "hiiii"),
			 new Book(1, authors.get(5), "Thriller", "https://www.gettyimages.com/photos/book", 100.20, LocalDate.now(), "The Hindu", true, "hiiii"),
			 new Book(1, authors.get(6), "Romantic", "https://www.gettyimages.com/photos/book", 100.20, LocalDate.now(), "The Hindu", true, "hiiii"),
			 new Book(1, authors.get(7), "COMIC", "https://www.gettyimages.com/photos/book", 100.20, LocalDate.now(), "The Hindu", true, "hiiii"));
	 
	 
	 public static List<Reader> readers = Arrays.asList(
			 new Reader(1, "readerA", "readerA@mail", books.subList(0, 3).stream().collect(Collectors.toSet())),
			 new Reader(2, "readerB", "readerB@mail", Collections.emptySet()),
			 new Reader(3, "readerC", "readerC@mail", books.subList(2, 6).stream().collect(Collectors.toSet())),
			 new Reader(4, "readerD", "readerD@mail", books.subList(5, 6).stream().collect(Collectors.toSet())),
			 new Reader(5, "readerE", "readerE@mail", books.subList(0, 2).stream().collect(Collectors.toSet())),
			 new Reader(6, "readerF", "readerF@mail", books.subList(3, 5).stream().collect(Collectors.toSet())),
			 new Reader(7, "readerG", "readerG@mail", books.subList(4, 6).stream().collect(Collectors.toSet())));
}
