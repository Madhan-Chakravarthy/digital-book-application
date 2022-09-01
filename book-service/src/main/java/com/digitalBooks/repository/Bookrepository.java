package com.digitalBooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalBooks.entity.Book;

public interface Bookrepository extends JpaRepository<Book, Integer> {

}
