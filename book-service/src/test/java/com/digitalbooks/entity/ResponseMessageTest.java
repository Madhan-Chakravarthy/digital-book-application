package com.digitalbooks.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResponseMessageTest {

	@InjectMocks
	ResponseMessage responseMessage;
	@Test
	void test() {
		responseMessage.setMessage("message");
		ResponseMessage responseMessage1= new ResponseMessage("message");
		assertEquals(responseMessage.getMessage(),"message");
		
		assertEquals(responseMessage.canEqual(responseMessage1), true);
		assertEquals(responseMessage.equals(responseMessage1), true);
		assertEquals(responseMessage.hashCode(), responseMessage1.hashCode());
		ResponseMessage responseMessage2= new ResponseMessage("message111");
	
		assertEquals(responseMessage.equals(responseMessage2), false);
		
	}

}
