package com.digitalbooks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceApplicationTest {

	@InjectMocks
	BookServiceApplication bookServiceApplication;
	@Test
	void test() {
		bookServiceApplication = new BookServiceApplication();
		bookServiceApplication.restTemplate();
		bookServiceApplication.bCryptPasswordEncoder();
	}

}
