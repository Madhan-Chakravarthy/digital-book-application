package com.digitalbooks.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.digitalbooks.service.AuthorService;

class BaseControllerTest {

	@Mock
	AuthorController authorController;
	@Test
	void testSaveForException() {
		assertThrows(NullPointerException.class, ()->{
			authorController.saveBook(5, null);
		});
	}
}
