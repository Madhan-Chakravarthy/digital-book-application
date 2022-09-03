package com.digitalbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbooks.entity.Book;



public interface Bookrepository extends JpaRepository<Book, Integer> {

	List<Book> findByCategoryIgnoreCaseOrPriceOrPublisherIgnoreCaseOrAuthorAuthorNameIgnoreCase(String category, Double price,String publisher,String authorName);
	List<Book> findByAuthorId(Integer id);
} 

