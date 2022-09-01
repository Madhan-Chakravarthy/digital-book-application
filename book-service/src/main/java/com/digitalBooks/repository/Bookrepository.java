package com.digitalBooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalBooks.entity.Book;

public interface Bookrepository extends JpaRepository<Book, Integer> {

	/*
	 * List<Book> findByCategBooksAndPriceAndPublisher(String category, Double
	 * price,String publisher); List<Book> findByCateBooksAndPublisher(String
	 * category,String publisher);
	 */
	
	List<Book> findByPublisher(String publisher);
	List<Book>  findByPriceAndPublisher(Double price, String publisher);
} 

