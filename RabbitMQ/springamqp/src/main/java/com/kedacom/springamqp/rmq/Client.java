package com.kedacom.springamqp.rmq;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 客户端
 * @author lihongguang
 * @date 2018年7月31日
 */
@Component
public class Client {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TopicExchange topic;

	/**
	 * 发送请求并返回结果
	 * @param index
	 * @return
	 */
	public Integer send(Integer index) {

		Integer response = (Integer) template.convertSendAndReceive(topic.getName(), "rpc.test", index);
		return response;

		// StringBuilder builder = new StringBuilder("Hello to ");
		// 以点计数
		/*
		 * dots++; for (int i = 0; i < dots; i++) { builder.append('.'); }
		 */
		// String key = keys[this.index];
		/*
		 * if (++this.index == 6) { this.index = 0; }
		 */
		// builder.append(key);
		// String message = builder.toString();
		// 通过Exchange发送
		// template.convertAndSend(topic.getName(), key, message);
		// logger.info("Sent '" + message + "'");
		// logger.info("Requesting fib(" + index + ")");
		// logger.info("Got fib(" + index + ")=" + response);
	}

	/*
	 * @Autowired private Queue queue;
	 */

	/*
	 * @Autowired private FanoutExchange fanout;
	 */

	/*
	 * @Autowired private DirectExchange direct;
	 */
	// private static Logger logger =
	// LoggerFactory.getLogger(Client.class.getSimpleName());
	// private int dots = 0;
	// private int index = 0;

	/*
	 * private final String[] keys = { "one", "two", "three" };
	 */
	/*
	 * private final String[] keys = { "quick.orange.rabbit",
	 * "lazy.orange.elephant", "quick.orange.fox", "lazy.brown.fox",
	 * "lazy.pink.rabbit", "quick.brown.fox" };
	 */

}
