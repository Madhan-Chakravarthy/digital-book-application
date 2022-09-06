package com.digitalbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbooks.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {

}
