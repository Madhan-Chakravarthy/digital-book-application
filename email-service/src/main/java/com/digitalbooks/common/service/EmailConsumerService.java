package com.digitalbooks.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.digitalbooks.common.AppConstants;
import com.digitalbooks.model.Author;
import com.digitalbooks.model.Book;
import com.digitalbooks.model.Reader;


@Service
public class EmailConsumerService {
	private final Logger logger 
	= LoggerFactory.getLogger(EmailConsumerService.class);

@KafkaListener(topics = AppConstants.TOPIC_NAME_EMAIL, groupId = AppConstants.GROUP_ID)
public void consume(Author author) {
	//error
	System.out.println("hiiii");
	logger.info(String.format("received email-> %s", author.toString()));
}
}





