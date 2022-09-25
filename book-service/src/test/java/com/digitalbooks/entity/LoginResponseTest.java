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
		loginResponse.setId((long) 1);
		loginResponse.setToken("pass");
		List<String> roles = new ArrayList<String>();
		roles.add(ERole.ROLE_AUTHOR.name());
		loginResponse.setRoles(roles);
		assertEquals(loginResponse.getToken(), "pass");
		assertEquals(loginResponse.getId(), (long) 1);
		assertEquals(loginResponse.getRoles(), roles);

		LoginResponse loginResponse1= new LoginResponse();
		loginResponse1.setId((long) 1);
		loginResponse1.setToken("pass");
		loginResponse1.setRoles(roles);
		assertEquals(loginResponse.canEqual(loginResponse1), true);
		assertEquals(loginResponse.equals(loginResponse1), true);
		assertEquals(loginResponse.hashCode(), loginResponse1.hashCode());
		loginResponse1.setId((long) 2);
		loginResponse1.setToken("pass123");
		assertEquals(loginResponse.equals(loginResponse1), false);
		assertNotEquals(loginResponse.hashCode(), loginResponse1.hashCode());

	}

}
