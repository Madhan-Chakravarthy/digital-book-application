package com.digitalBooks.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Book {

	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Author author;
	@Column (nullable = false)
	private String category;
	@Column
	private String image;
	@Column
	private Double price;
	@Column
	private LocalDate publishDate;
	@Column
	private String publisher;
	@Column (nullable =false)
	private Boolean active;
	@Column(nullable = false)
	private String content;
	
	public Book(Integer id, Author author, String category, String image, Double price, LocalDate publishDate,
			String publisher, Boolean active, String content) {
		super();
		this.id = id;
		this.author = author;
		this.category = category;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.publisher = publisher;
		this.active = active;
		this.content = content;
	}
	public Book() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDate getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	

}
