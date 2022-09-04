package com.digitalbooks.entity;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Reader {

	@Id
	@GeneratedValue
	private Integer id;
	@Column (nullable = false)
	private String readerName;
	@Column (unique = true, nullable = false)
	private String readerMailId;
	@OneToMany (targetEntity = Book.class)
	private Set<Book> purchasedBooks;
	
	public Reader(Integer id, String readerName, String readerMailId, Set<Book> purchasedBooks) {
		super();
		this.id = id;
		this.readerName = readerName;
		this.readerMailId = readerMailId;
		this.purchasedBooks = purchasedBooks;
	}
	public Reader() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getReaderMailId() {
		return readerMailId;
	}
	public void setReaderMailId(String readerMailId) {
		this.readerMailId = readerMailId;
	}
	public Set<Book> getPurchasedBooks() {
		return purchasedBooks;
	}
	public void setPurchasedBooks(Set<Book> books) {
		this.purchasedBooks = books;
	}
	
}
