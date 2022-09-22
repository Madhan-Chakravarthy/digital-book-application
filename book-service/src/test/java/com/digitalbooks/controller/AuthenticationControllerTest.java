package com.digitalbooks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.digitalbooks.entity.Author;
import com.digitalbooks.entity.ERole;
import com.digitalbooks.entity.Role;
import com.digitalbooks.entity.SignupRequest;
import com.digitalbooks.entity.User;
import com.digitalbooks.service.AuthorService;
import com.digitalbooks.service.impl.AuthorServiceImpl;
import com.digitalbooks.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

	@InjectMocks
	AuthenticationController authenticationController;
	
	@Mock
	UserServiceImpl userService;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@Mock
	AuthorServiceImpl authorService;
	@Test
	void testAuthenticateUser() {
		
	}
	
	@Test
	void testSignup() {
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setAboutAuthor("about");
		signupRequest.setEmail("email");
		signupRequest.setName("name");
		signupRequest.setPassword("pass");
		Set< String> roles = new HashSet<String>();
		signupRequest.setRole(roles );
		signupRequest.setUsername("username");
		when(userService.validateUserEmail("email")).thenReturn(true);
		assertEquals(authenticationController.signup(signupRequest), new ResponseEntity<>("Email already exist!",HttpStatus.BAD_REQUEST));
		signupRequest.setEmail("email1");
		when(userService.validateUserEmail("email1")).thenReturn(false);
		when(userService.validateUserName("username")).thenReturn(true);
		assertEquals(authenticationController.signup(signupRequest), new ResponseEntity<>("User name already exist!",HttpStatus.BAD_REQUEST));
		signupRequest.setUsername("username1");
		when(userService.validateUserName("username1")).thenReturn(false);
		assertEquals(authenticationController.signup(signupRequest), new ResponseEntity<>("Select atleast one role!",HttpStatus.BAD_REQUEST));
		roles.add("author");
		roles.add("reader");
		when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("pass");
		when(userService.findRole(ERole.ROLE_AUTHOR)).thenReturn(new Role());
		 Author author = new Author();
		  author.setName(signupRequest.getName());
		  author.setAboutAuthor(signupRequest.getAboutAuthor());
		when(authorService.saveAuthor(author)).thenReturn(new Author());
		
		when(userService.findRole(ERole.ROLE_READER)).thenReturn(new Role());
		
		assertEquals(authenticationController.signup(signupRequest), new ResponseEntity<>(HttpStatus.OK));

	}

}
