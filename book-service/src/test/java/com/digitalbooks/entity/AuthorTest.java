package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorTest {

	@InjectMocks
	Author author;
	@Test
	void test() {
		author.setId(1);
		author.setName("Author1");
		author.setAboutAuthor("about author");
		
		assertEquals(author.getId(), 1);
		assertEquals(author.getName(), "Author1");
		assertEquals(author.getAboutAuthor(), "about author");
		
		Author author1=new Author();
		author1.setId(1);
		author1.setName("Author1");
		author1.setAboutAuthor("about author");
		assertEquals(author.canEqual(author1), true);
		assertEquals(author.equals(author1), true);
		assertEquals(author.hashCode(), author1.hashCode());
		author1.setId(2);
		author1.setName("Author2");
		author1.setAboutAuthor("about author2");
		assertEquals(author.equals(author1), false);
		assertNotEquals(author.hashCode(), author1.hashCode());
		
		
	}

}
