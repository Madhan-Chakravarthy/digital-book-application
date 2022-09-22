package com.digitalbooks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author madhan Author class is annotated with entity.It is responsible to
 *         create the Author data in Database
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Author {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	@Size(message = "Author name should not be empty")
	private String name;
	@Column
	private String aboutAuthor;
}
