package com.digitalbooks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.digitalbooks.common.AppConstants;
import com.digitalbooks.model.Author;

@Service
public class KafkaProducerEmailService {

	private static final Logger logger = 
			LoggerFactory.getLogger(KafkaProducerEmailService.class);
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(Author author) 
	{
		logger.info(String.format("Message sent -> %s", author));
		this.kafkaTemplate.send(AppConstants.TOPIC_NAME_EMAIL, author);
	}

}
