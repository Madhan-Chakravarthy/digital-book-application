package com.digitalbooks.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalbooks.mockData.MockData;
import com.digitalbooks.service.impl.UserDetailsImpl;

@ExtendWith(MockitoExtension.class)
class UserDetailsTest {

	@Mock
	UserDetailsImpl userDetails;
	
	MockData mockData=new MockData();
	
	@BeforeEach
	void test() {
		userDetails.build(mockData.users.get(0));
	}
	@Test
	void testOtherMethod() {
		userDetails.build(mockData.users.get(0));
		UserDetailsImpl userDetails1  = new UserDetailsImpl((long)1, "username", "email", "password", null);
		assertEquals(userDetails1.getId(),(long)1);
		assertEquals(userDetails1.getEmail(), "email");
		assertEquals(userDetails1.getPassword(),"password");
		assertEquals(userDetails1.getUsername(),"username");
		assertEquals(userDetails1.getAuthorities(),null);
		assertEquals(userDetails1.isAccountNonExpired(),true);
		assertEquals(userDetails1.isAccountNonLocked(),true);
		assertEquals(userDetails1.isCredentialsNonExpired(),true);
		assertEquals(userDetails1.isEnabled(),true);
		assertEquals(userDetails1.equals(null),false);
		assertEquals(userDetails1.equals(userDetails1),true);
		UserDetailsImpl userDetails2  = new UserDetailsImpl((long)1, "username11", "email", "password", null);

		assertEquals(userDetails1.equals(userDetails2),true);
	}

}
