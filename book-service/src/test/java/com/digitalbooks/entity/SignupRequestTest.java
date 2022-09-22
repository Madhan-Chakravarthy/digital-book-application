package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignupRequestTest {

	@InjectMocks
	SignupRequest signupRequest;
	@Test
	void test() {
		signupRequest.setAboutAuthor("about");
		signupRequest.setEmail("email");
		signupRequest.setName("name");
		signupRequest.setPassword("pass");
		Set< String> roles = new HashSet<String>();
		
		signupRequest.setRole(roles );
		signupRequest.setUsername("username");
		assertEquals(signupRequest.getEmail(),"email");
		assertEquals(signupRequest.getAboutAuthor(),"about");
		assertEquals(signupRequest.getName(),"name");
		assertEquals(signupRequest.getPassword(),"pass");
		assertEquals(signupRequest.getUsername(),"username");
		assertEquals(signupRequest.getRole(),roles);
		
	}

}
