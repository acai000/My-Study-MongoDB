package com.example.mongodb;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mongodb.activemq.Producer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestActiveMQ {
	
	@Autowired
	private Producer producer;
	
	@Test
	public void test() {
		Destination destination = new ActiveMQQueue("mongoSend.queue");
		producer.sendMessage(destination, "Hello,ActiveMQ");
	}
}
