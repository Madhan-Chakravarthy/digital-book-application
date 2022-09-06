package com.digitalbooks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * @author madhan Author class is annotated with entity.It is responsible to
 *         create the Author data in Database
 *
 */
@Data
@Entity
public class Author {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	@Size(message = "Author name should not be empty")
	private String name;
	@Column(unique = true, nullable = false)
	@Email(message = "please enter valid email")
	private String mailId;
}
