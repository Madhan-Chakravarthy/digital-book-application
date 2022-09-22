package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.digitalbooks.common.BookCategory;

@ExtendWith(MockitoExtension.class)
class BookTest {

	@InjectMocks
	Book book;
	@Test
	void test() {
		Author author =new Author();
		book.setId(1);
		book.setActive(true);
		book.setAuthor(author);
		book.setTittle("first Book");
		book.setCategory(BookCategory.COMIC);
		book.setContent("first book content");
		book.setImage("url");
		book.setPrice(100.3);
		book.setPublishDate("12-11-2021");
		book.setPublisher("Publisher A");
		book.setPurchased(false);
		assertEquals(book.getId(), 1);
		assertEquals(book.getAuthor(), author);
		assertEquals(book.getTittle(), "first Book");
		assertEquals(book.getCategory(), BookCategory.COMIC);
		assertEquals(book.getContent(), "first book content");
		assertEquals(book.getImage(), "url");
		assertEquals(book.getPrice(), 100.3);
		assertEquals(book.getPublishDate(), "12-11-2021");
		assertEquals(book.getPublisher(), "Publisher A");
		assertEquals(book.getPurchased(), false);
	}

}
