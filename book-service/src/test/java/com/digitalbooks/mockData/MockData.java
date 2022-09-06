package com.digitalbooks.mockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Author;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Reader;

public class MockData {

	public  List<Author> authors = new ArrayList<Author>();

	public  List<Book> books = new ArrayList<Book>();

	public  List<Reader> readers = new ArrayList<Reader>();

public MockData(){
		Author author = new Author();
		author.setId(1);
		author.setName("Author1");
		author.setMailId("author1@gmail.com");
		authors.add(author);
		Author author1 = new Author();
		author1.setId(2);
		author1.setName("Author2");
		author1.setMailId("author2@gmail.com");
		authors.add(author1);

		Book book = new Book();
		book.setId(1);
		book.setActive(true);
		book.setAuthor(authors.get(0));
		book.setTittle("first Book");
		book.setCategory(BookCategory.COMIC);
		book.setContent("first book content");
		book.setImage("url");
		book.setPrice(100.3);
		book.setPublishDate(LocalDate.now());
		book.setPublisher("Publisher A");
		books.add(book);

		Book book1 = new Book();
		book1.setId(2);
		book1.setActive(true);
		book1.setAuthor(authors.get(1));
		book1.setTittle("Second Book");
		book1.setCategory(BookCategory.COMIC);
		book1.setContent("second book content");
		book1.setImage("url");
		book1.setPrice(200.3);
		book1.setPublishDate(LocalDate.now());
		book1.setPublisher("Publisher B");
		books.add(book1);

		Book book2 = new Book();
		book2.setId(2);
		book2.setActive(true);
		book2.setAuthor(authors.get(1));
		book2.setTittle("Second Book");
		book2.setCategory(BookCategory.COMIC);
		book2.setContent("second book content");
		book2.setImage("url");
		book2.setPrice(200.3);
		book2.setPublishDate(LocalDate.now());
		book2.setPublisher("Publisher B");
		books.add(book2);

		Reader reader = new Reader();
		reader.setId(1);
		reader.setReaderMailId("readerA@gmail.com");
		reader.setPurchasedBooks(books.stream().collect(Collectors.toSet()));
		reader.setReaderName("Reder2");
		readers.add(reader);

		Reader reader1 = new Reader();
		reader1.setId(2);
		reader1.setReaderMailId("readerA@gmail.com");
		reader1.setPurchasedBooks(books.stream().collect(Collectors.toSet()));
		reader1.setReaderName("Reder2");
		readers.add(reader1);

	}

}
