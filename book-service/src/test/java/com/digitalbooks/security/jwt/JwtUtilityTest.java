package com.digitalbooks.security.jwt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JwtUtilityTest {

	@InjectMocks
	JwtUtility jwtUtility;
	String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhdXRob3IiLCJpYXQiOjE2NjM3MzgyNzAsImV4cCI6MTY2NDA5ODI3MH0.z2OWZcsCNV5Yw3OS1H3Vab2UYb4itopAJZLmjk1RVsydQWMX89ea0fXlDup8dpuVnBNlqIDl0e8mntpxkwdPBw";

	/*
	 * @Test void testGetUsernameFromToken() {
	 * jwtUtility.getUsernameFromToken(token); String s=""; }
	 */

	/*
	 * @Test void testIsTokenExpired() { jwtUtility.isTokenExpired(token); }
	 */
}
