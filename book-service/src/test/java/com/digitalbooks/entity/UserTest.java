package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalbooks.mockData.MockData;

@ExtendWith(MockitoExtension.class)
class UserTest {

	@InjectMocks
	User user;
	
	MockData mockData=new MockData();
	@Test
	void test() {
		user.setAuthor(mockData.authors.get(0));
		user.setEmail("email");
		user.setId((long)1);
		user.setPassword("pass");
		Set<Role> roles = new HashSet<Role>();
		user.setRoles(roles);
		user.setUsername("username");
		assertEquals(user.getAuthor(),mockData.authors.get(0));
		assertEquals(user.getEmail(),"email");
		assertEquals(user.getPassword(),"pass");
		assertEquals(user.getRoles(),roles);
		assertEquals(user.getUsername(),"username");
		
		User user1= new User();
		user1.setAuthor(mockData.authors.get(0));
		user1.setEmail("email");
		user1.setId((long)1);
		user1.setPassword("pass");
		user1.setRoles(roles);
		user1.setUsername("username");
		
		assertEquals(user.getId(),(long)1);
		assertEquals(user.canEqual(user1), true);
		assertEquals(user.equals(user1), true);
		assertEquals(user.hashCode(), user1.hashCode());
		
		user1.setAuthor(mockData.authors.get(1));
		user1.setEmail("email1");
		user1.setId((long)2);
		user1.setPassword("pass1");

		assertEquals(user.equals(user1), false);
	}

}
