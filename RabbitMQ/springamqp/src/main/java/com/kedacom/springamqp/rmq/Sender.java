package com.kedacom.springamqp.rmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 发送
 * @author lihongguang
 * @date 2018年7月31日
 */
@Component
public class Sender {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	public void send() {
		String message = "Hello World!";
		template.convertAndSend(queue.getName(), message);
		System.out.println(" [x] Sent '" + message + "'");
	}
}
