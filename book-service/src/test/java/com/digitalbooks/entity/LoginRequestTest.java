package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginRequestTest {

	@InjectMocks
	LoginRequest loginRequest;
	@Test
	void test() {
		loginRequest.setUsername("user");
		loginRequest.setPassword("pass");
		assertEquals(loginRequest.getPassword(),"pass");
		assertEquals(loginRequest.getUsername(), "user");
	}

}
