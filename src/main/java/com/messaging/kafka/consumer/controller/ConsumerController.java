package com.messaging.kafka.consumer.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@CrossOrigin
@RestController
public class ConsumerController {

	public String message;
	
	@KafkaListener(topics = "DISCHARGE_TOPIC", groupId = "group_id")
	public void consumer(String message) {
		this.message = message;
		System.out.println("Consumed message: " + this.message);
	}
	
	@GetMapping("/getMessageBills")
	public String getMessage() {
		return "Patient " + this.message + " has been discharged. Bills have been sent over";
	}
	
	@GetMapping("/getMessagePharma")
	public String getMessagePharma() {
		return "Patient " + this.message + " has been discharged. Medicine list have been sent over";
	}
	
	@GetMapping("/getMessageClaims")
	public String getMessageClaims() {
		return "Patient " + this.message + " has been discharged. Claims have been filed";
	}
	
}
