package com.digitalbooks.model;

import java.util.Set;

import lombok.Data;


@Data
public class Reader {
	private Integer id;
	private String readerName;
	private String readerMailId;
	private Set<Book> purchasedBooks;
}
