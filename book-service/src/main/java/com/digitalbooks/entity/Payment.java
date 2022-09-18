package com.digitalbooks.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Payment {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private LocalDate date;
	@Column
	private LocalTime time;
	@ManyToOne
	private User user;
	@OneToMany(targetEntity = Book.class)
	private Set<Book> purchasedBooks;
	
	
	
}
