package com.digitalbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbooks.entity.Author;



public interface AuthorRepository extends JpaRepository<Author, Integer>{

	List<Author> findByAuthorName (String authorName);
}
