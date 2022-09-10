package com.digitalbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.model.Author;
import com.digitalbooks.service.KafkaProducerEmailService;



@RestController
@RequestMapping("/kafka/email")
public class KafkaProducerEmailController {
	private final KafkaProducerEmailService kafkaProducerEmailService;

	@Autowired
	public KafkaProducerEmailController(KafkaProducerEmailService kafkaProducerEmailService) {
		this.kafkaProducerEmailService = kafkaProducerEmailService;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic( @RequestBody Author author) {
		this.kafkaProducerEmailService.sendMessage(author);
	}
}
