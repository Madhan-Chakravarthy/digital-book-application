package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoleTest {

	@InjectMocks
	Role role;
	@Test
	void test() {
		role.setId(1);
		role.setName(ERole.ROLE_AUTHOR);
		assertEquals(role.getId(),1);
		assertEquals(role.getName(),ERole.ROLE_AUTHOR);
		Role role1= new Role();
		role1.setId(1);
		role1.setName(ERole.ROLE_AUTHOR);
		assertEquals(role.getId(),1);
		assertEquals(role.canEqual(role1), true);
		assertEquals(role.equals(role1), true);
		assertEquals(role.hashCode(), role1.hashCode());
		
		role1.setId(2);
		role1.setName(ERole.ROLE_READER);

		assertEquals(role.equals(role1), false);
		assertNotEquals(role.hashCode(), role1.hashCode());
	}

}
