package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginResponseTest {
	
	@InjectMocks
	LoginResponse loginResponse;

	@Test
	void test() {
		loginResponse.setId((long)1);
		loginResponse.setToken("pass");
		List<String> roles = new ArrayList<String>();
		roles.add(ERole.ROLE_AUTHOR.name());
		loginResponse.setRoles(roles);
		assertEquals(loginResponse.getToken(),"pass");
		assertEquals(loginResponse.getId(), (long)1);
		assertEquals(loginResponse.getRoles(), roles);
		/*
		 * assertEquals(loginResponse.canEqual(loginResponse), true);
		 * assertEquals(loginResponse.equals(loginResponse), true);
		 * assertEquals(loginResponse.hashCode(), loginResponse.hashCode());
		 */
	}

}
