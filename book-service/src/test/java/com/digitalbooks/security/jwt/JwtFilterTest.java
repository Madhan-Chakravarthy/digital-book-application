package com.digitalbooks.security.jwt;


import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.digitalbooks.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class JwtFilterTest {


	  private static final String token = "bearer 260bce87-6be9-4897-add7-b3b675952538";
	    private static final String testUri = "/testUri";

		@InjectMocks
		JwtFilter jwtFilter;
		
		@Mock
		JwtUtility jwtUtility;
		
		@Mock
		UserServiceImpl userService;
		

	    @Test
	    public void testDoFilterInternalPositiveScenarioWhenTokenIsInHeader() throws ServletException, IOException {
	        MockHttpServletRequest request = new MockHttpServletRequest();
	        request.addHeader("Authorization", token);
	        String auth = token.substring(7);
	        request.setRequestURI(testUri);
	        MockHttpServletResponse response = new MockHttpServletResponse();
	        MockFilterChain filterChain = new MockFilterChain();
	        jwtFilter.doFilterInternal(request, response, filterChain);
	      
	    }
}
