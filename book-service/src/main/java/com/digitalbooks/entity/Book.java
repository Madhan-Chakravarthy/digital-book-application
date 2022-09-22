package com.digitalbooks.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.digitalbooks.common.BookCategory;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author madhan Book class is annotated with entity.It is responsible to
 *         create the Book data in Database
 *
 */
@Getter
@Setter
@Entity
public class Book {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Author author;
	@Column
	@NotNull(message = "Enter the book name")
	private String tittle;
	@Enumerated(EnumType.STRING)
	private BookCategory category;
	@Column
	private String image;
	@Column
	@Min(value = 0, message = "price should not be negative value")
	private Double price;
	@Column
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String publishDate;
	@Column
	private String publisher;
	@Column (nullable = false)
	private Boolean active;
	@Column(nullable = false)
	private String content;
	
	private Boolean purchased;
}
