package com.digitalbooks.mockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Author;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.ERole;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.entity.Role;
import com.digitalbooks.entity.User;

public class MockData {

	public  List<Author> authors = new ArrayList<Author>();

	public  List<Book> books = new ArrayList<Book>();

	public  List<User> users = new ArrayList<User>();
	
	public  List<Payment> payments = new ArrayList<Payment>();

public MockData(){
		Author author = new Author();
		author.setId(1);
		author.setName("Author1");
		author.setAboutAuthor("about author");
		authors.add(author);
		Author author1 = new Author();
		author1.setId(2);
		author1.setName("Author2");
		author1.setAboutAuthor("about author");
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
		book.setPublishDate("12-11-2021");
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
		book1.setPublishDate("12-11-2021");
		book1.setPublisher("Publisher B");
		books.add(book1);

		Book book2 = new Book();
		book2.setId(3);
		book2.setActive(true);
		book2.setAuthor(authors.get(1));
		book2.setTittle("Second Book");
		book2.setCategory(BookCategory.COMIC);
		book2.setContent("second book content");
		book2.setImage("url");
		book2.setPrice(200.3);
		book2.setPublishDate("12/11/2021");
		book2.setPublisher("Publisher B");
		books.add(book2);

		User user1 = new User();
		user1.setId((long) 1);
		user1.setEmail("user1@email.com");
		user1.setPassword("123456");
		user1.setAuthor(author);
		user1.setUsername("user1");
	    Set<Role> roles = new HashSet<Role>();
	    Role role = new Role();
	    role.setId(1);
	    role.setName(ERole.ROLE_AUTHOR);
	    roles.add(role);
		user1.setRoles(roles);
		users.add(user1);
		
		User user2 = new User();
		user2.setId((long) 1);
		user2.setEmail("user1@email.com");
		user2.setPassword("123456");
		user2.setAuthor(author);
		user2.setUsername("user1");
	    Set<Role> roles1 = new HashSet<Role>();
	    Role role1 = new Role();
	    role1.setId(2);
	    role1.setName(ERole.ROLE_READER);
	    roles1.add(role);
	    roles1.add(role1);
		user2.setRoles(roles);
		users.add(user2);
		
		Payment payment = new Payment();
		payment.setId(null);
		payment.setDate(LocalDate.now());
		payment.setUser(user2);
		payment.setPurchasedBooks(new HashSet<Book>(books));
		payments.add(payment);
		
		Payment payment1 = new Payment();
		payment1.setId((long) 2);
		payment1.setDate(LocalDate.now());
		payment1.setUser(user2);
		payment1.setPurchasedBooks(Collections.EMPTY_SET);
		payments.add(payment1);
		
		
		

	}

}
