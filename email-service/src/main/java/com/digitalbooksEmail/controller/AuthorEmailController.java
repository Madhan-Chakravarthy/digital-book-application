package com.digitalbooksEmail.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooksEmail.dto.Author;

@RestController
@RequestMapping("/email/author")
public class AuthorEmailController {

	@PostMapping ("/upload/success")
	public String bookUploadSuccess(Author author) {
		return "Book uploaded successfully";
		
	}
	@PostMapping ("/upload/failure")
	public String bookUploadFailure(Author author) {
		return "Failed to upload the book";	
	}
}
