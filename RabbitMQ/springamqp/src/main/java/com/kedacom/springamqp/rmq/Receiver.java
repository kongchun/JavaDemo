package com.kedacom.springamqp.rmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 监听
 * @author lihongguang
 * @date 2018年7月31日
 */
@RabbitListener(queues = "myqueue")
@Component
public class Receiver {

	@RabbitHandler
	public void receive(String in) {
		System.out.println(" [x] Received '" + in + "'");
	}

}
