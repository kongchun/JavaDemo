package com.kedacom.springamqp.rmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
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

	/*
	 * @Autowired private Queue queue;
	 */

	@Autowired
	private FanoutExchange fanout;

	private static Logger logger = LoggerFactory.getLogger(Sender.class.getSimpleName());
	private int dots = 0;

	public void send() {
		StringBuilder builder = new StringBuilder("Hello");
		// 以点计数
		dots++;
		for (int i = 0; i < dots; i++) {
			builder.append('.');
		}
		String message = builder.toString();
		// 通过Exchange发送
		template.convertAndSend(fanout.getName(), "", message);
		logger.info("Sent '" + message + "'");
	}
}
