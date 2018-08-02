package com.kedacom.springamqp.rmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
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

	/*
	 * @Autowired private FanoutExchange fanout;
	 */

	@Autowired
	private DirectExchange direct;

	private static Logger logger = LoggerFactory.getLogger(Sender.class.getSimpleName());
	// private int dots = 0;
	private int index = 0;
	private final String[] keys = {
			"one", "two", "three"
	};

	public void send() {
		StringBuilder builder = new StringBuilder("Hello to ");
		// 以点计数
		/*
		 * dots++; for (int i = 0; i < dots; i++) { builder.append('.'); }
		 */
		String key = keys[this.index];
		if (++this.index == 3) {
			this.index = 0;
		}
		builder.append(key);
		String message = builder.toString();
		// 通过Exchange发送
		template.convertAndSend(direct.getName(), key, message);
		logger.info("Sent '" + message + "'");
	}
}
