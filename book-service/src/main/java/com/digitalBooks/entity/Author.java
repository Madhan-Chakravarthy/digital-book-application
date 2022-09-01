package com.digitalBooks.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author {

	@Id
	@GeneratedValue
	private Integer id;
	@Column (nullable = false)
	private String authorName;
	@Column (nullable = false)
	private String authorMailId;
	@OneToMany (targetEntity = Book.class)
	private Set<Book> books;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorMailId() {
		return authorMailId;
	}
	public void setAuthorMailId(String authorMailId) {
		this.authorMailId = authorMailId;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
}
