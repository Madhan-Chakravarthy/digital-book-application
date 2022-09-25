package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalbooks.mockData.MockData;

@ExtendWith(MockitoExtension.class)
class PayamentTest {

	@InjectMocks
	Payment payment;
	
	MockData mockdata = new MockData();
	
	@Test
	void test() {
		payment.setDate(LocalDate.now());
		payment.setId((long)5);
		payment.setUser(mockdata.users.get(0));
		payment.setPurchasedBooks( new HashSet<Book>( mockdata.books));
		
		Payment payment1 = new Payment();
		payment1.setDate(LocalDate.now());
		payment1.setId((long)5);
		payment1.setUser(mockdata.users.get(0));
		payment1.setPurchasedBooks( new HashSet<Book>( mockdata.books));
		
		assertEquals(payment.getDate(),LocalDate.now());
		assertEquals(payment.getUser(),mockdata.users.get(0));
		assertEquals(payment.getId(), (long)5);
		assertEquals(payment.getPurchasedBooks(),  new HashSet<Book>( mockdata.books));
		
		assertEquals(payment.canEqual(payment1), true);
		assertEquals(payment.equals(payment1), true);
		assertEquals(payment.hashCode(), payment1.hashCode());
		
		payment1.setDate(LocalDate.now());
		payment1.setId((long)9);
		payment1.setUser(mockdata.users.get(1));
		
		assertEquals(payment.equals(payment1), false);
		assertNotEquals(payment.hashCode(), payment1.hashCode());

	}

}
