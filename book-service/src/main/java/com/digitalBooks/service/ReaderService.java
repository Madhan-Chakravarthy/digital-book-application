package com.digitalBooks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalBooks.entity.Book;
import com.digitalBooks.repository.Bookrepository;

@Service
public class ReaderService {
	@Autowired
	Bookrepository bookrepository;
	
	public List<Book> getBooks(String category,String author, Double price,String publisher) {
		List<Book> books = bookrepository.findByPriceAndPublisher(price, publisher);
		/*
		 * if(category !=null && author !=null && price !=null && publisher != null) {
		 * books= bookrepository.findByPriceAndPublisher(price, publisher); }else if
		 * (category !=null && price !=null && publisher != null){
		 * books=bookrepository.findByCategBooksAndPriceAndPublisher(category, price,
		 * publisher); }else if (category !=null && publisher != null){ books=
		 * bookrepository.findByCateBooksAndPublisher(category, publisher); }else {
		 * books=bookrepository.findAll(); }
		 */
	//	List<Book> book = .getBooks(category, author,price,publisher);
		return books;
	}


	public Optional<Book> getBookById(Integer id) {
		// TODO Auto-generated method stub
		return bookrepository.findById(id);
	}
}
