package com.digitalbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByAuthorId(Integer id);

	List<Book> findByCategory(BookCategory category);

	List<Book> findByTittleIgnoreCaseOrPublisherIgnoreCaseOrAuthorNameIgnoreCase(String tittle, String publisher,
			String name);

	List<Book> findByTittleIgnoreCaseOrCategoryOrPriceOrPublisherIgnoreCaseOrAuthorNameIgnoreCase(String tittle,
			BookCategory category, Double price, String publisher, String name);
}
