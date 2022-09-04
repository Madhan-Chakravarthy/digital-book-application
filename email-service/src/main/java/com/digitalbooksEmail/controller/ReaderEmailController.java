package com.digitalbooksEmail.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooksEmail.dto.Reader;

@RestController
@RequestMapping("/email/reader")
public class ReaderEmailController {
	@PostMapping ("/buy/success")
	public String purchaseSuccess(Reader reader) {
		return "Thanks to purchase the book";
		
	}
	@PostMapping ("/buy/failure")
	public String purchaseFailure(Reader reader) {
		return "Purchase Failed";	
	}
}
