package com.digitalbooks.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.digitalbooks.common.BookCategory;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Book {
	private Integer id;
	private Author author;
	private String tittle;
	private BookCategory category;
	private String image;
	private Double price;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate publishDate;
	private String publisher;
	private Boolean active;
	private String content;
}
