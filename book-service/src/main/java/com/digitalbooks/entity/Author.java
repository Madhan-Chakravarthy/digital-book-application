package com.digitalbooks.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Author {

	@Id
	@GeneratedValue
	private Integer id;
	@Column (nullable = false)
	private String authorName;
	@Column (unique = true, nullable = false)
	private String authorMailId;
	
	public Author(Integer id, String authorName, String authorMailId) {
		super();
		this.id = id;
		this.authorName = authorName;
		this.authorMailId = authorMailId;
	}
	public Author() {
		super();
	}
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
	
}
