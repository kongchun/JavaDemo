package com.kedacom.springamqp.rmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * RabbitMQ 监听
 * @author lihongguang
 * @date 2018年7月31日
 */
@RabbitListener(queues = "myqueue")
public class Receiver {

	private final int instance;

	public Receiver(int i) {
		this.instance = i;
	}

	private static Logger logger = LoggerFactory.getLogger("Receiver Instance");

	@RabbitHandler
	public void receive(String in) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		logger.info(this.instance + "  Received '" + in + "'");
		doWork(in);
		watch.stop();
		logger.info(this.instance + "  Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				// 模拟复杂任务阻塞进程
				Thread.sleep(1000);
			}
		}
	}

}
