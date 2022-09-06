package com.digitalbooks.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
/**
 * 
 * @author madhan Reader class is annotated with entity.It is responsible to
 *         create the Reader data in Database
 *
 */
@Data
@Entity
public class Reader {

	@Id
	@GeneratedValue
	private Integer id;
	@Column
	@NotNull(message = "Please the Reader Name")
	private String readerName;
	@Column(unique = true, nullable = false)
	@Email(message = "please enter valid email")
	private String readerMailId;
	@OneToMany(targetEntity = Book.class)
	private Set<Book> purchasedBooks;
}
