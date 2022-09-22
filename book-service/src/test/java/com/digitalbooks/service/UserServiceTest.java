package com.digitalbooks.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.digitalbooks.entity.ERole;
import com.digitalbooks.entity.Role;
import com.digitalbooks.mockData.MockData;
import com.digitalbooks.repository.RoleRepository;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.service.impl.UserDetailsImpl;
import com.digitalbooks.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	RoleRepository roleRepository;
	
	@Mock
	UserDetailsImpl userDetails;
	
	MockData mockdata = new MockData();
	
	@Test
	void testSignup() {
		when(userRepository.save(mockdata.users.get(1))).thenReturn(mockdata.users.get(1));
		assertEquals(userService.signup(mockdata.users.get(1)),mockdata.users.get(1));
	}

	@Test
	void testValidateUserEmail() {
		when(userRepository.existsByEmail("author@gmail.com")).thenReturn(true);
		assertEquals(userService.validateUserEmail("author@gmail.com"),true);
	}
	
	@Test
	void testFindRole() {
		Role role = new Role();
		when(roleRepository.findByName(ERole.ROLE_AUTHOR)).thenReturn(role);
		assertEquals(userService.findRole(ERole.ROLE_AUTHOR),role);
	}
	
	@Test
	void testTalidateUserName() {
		when(userRepository.existsByUsername("madhan")).thenReturn(true);
		assertEquals(userService.validateUserName("madhan"),true);
	}
	
	@Test
	void testGetUser() {
		when(userRepository.findByUsername("user1")).thenReturn(mockdata.users.get(0));
		assertEquals(userService.getUser("user1"),mockdata.users.get(0));
	}
	
	@Test
	void testGetUsers() {
		when(userRepository.findAll()).thenReturn(mockdata.users);
		assertEquals(userService.getUsers(),mockdata.users);
	}
	
	@Test
	void testLoadUserByUsername() {
		when( userRepository.findByUsername("user5")).thenReturn(null);
		assertThrows(UsernameNotFoundException.class,()->{
			
			userService.loadUserByUsername("user5");
			});
		when( userRepository.findByUsername("user1")).thenReturn(mockdata.users.get(0));
		//when( userDetails.build(mockdata.users.get(0))).thenReturn(null);
		System.out.println("******" +   userService.loadUserByUsername("user1").toString());
		userService.loadUserByUsername("user1");
	}
	
}
