package com.demo.decision;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ContractConsumer {

	private final Logger logger = LoggerFactory.getLogger(ContractConsumer.class);
	private static final String CONSUMER_TOPIC = "ApprovedApplicationTopic";


	@KafkaListener(topics = CONSUMER_TOPIC, groupId = "contractConsumer")
	public void consume(ConsumerRecord<String, String> record) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		ObjectMapper mapper = new ObjectMapper();
		JSONObject json = (JSONObject) parser.parse(record.value());
		
		
		String applicationId = record.key();
		String email = (String) json.get("email");
		logger.info(String.format("Reading application from %s : \n%s", CONSUMER_TOPIC,json));
		logger.info("Contract Sent.");

	}

}