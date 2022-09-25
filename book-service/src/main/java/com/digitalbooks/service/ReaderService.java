package com.digitalbooks.service;

import java.util.List;
import java.util.Set;

import com.digitalbooks.common.BookCategory;
import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;
import com.digitalbooks.entity.PaymentRequest;

public interface ReaderService {
	public List<Book> searchBooks(String tittle, BookCategory category, Double price, String publisher, String name);
	public Set<Book> getPurchasedBooks(Long userId);
	public Payment purchaseBook( Long userId,PaymentRequest paymentRequest);
	public Book getBookById( Long userId,Integer bookId) ;
	public List<Book> simpleSearchBooks(String searchParam);
}
