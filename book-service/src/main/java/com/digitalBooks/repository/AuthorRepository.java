package com.digitalBooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalBooks.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}