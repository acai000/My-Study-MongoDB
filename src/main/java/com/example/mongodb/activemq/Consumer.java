package com.example.mongodb.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * mongoDB消费者
 * @author ZQC
 * 2018-03-22
 *
 */
@Component
public class Consumer {
	@JmsListener(destination = "zqcSend.queue")
	@SendTo("mongoReply.queue")
	public String receiveQueue(String text) {
		System.out.println("Mongo收到的报文为:"+text);
		return "Mongo已收到！";
	}
}
