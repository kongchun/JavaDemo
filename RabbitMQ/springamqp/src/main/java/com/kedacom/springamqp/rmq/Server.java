package com.kedacom.springamqp.rmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 服务端
 * @author lihongguang
 * @date 2018年7月31日
 */
//@RabbitListener(queues = "myqueue")
@Component
public class Server {

	/*
	 * private final int instance; public Receiver(int i) { this.instance = i; }
	 */

	// private static Logger logger = LoggerFactory.getLogger("Receiver
	// Instance");
	private static Logger logger = LoggerFactory.getLogger(Server.class.getSimpleName());

	// 为两个队列添加监听
	/*
	 * @RabbitListener(queues = "#{autoDeleteQueue1.name}") public void
	 * receive1(String in) throws InterruptedException { receive(in, 1); }
	 * @RabbitListener(queues = "#{autoDeleteQueue2.name}") public void
	 * receive2(String in) throws InterruptedException { receive(in, 2); }
	 */

	@RabbitListener(queues = "demo.rpc.requests")
	public int fibonacci(int n) {
		logger.info("Received request for fib(" + n + ")");
		int result = fib(n);
		logger.info("Returned " + result);
		return result;
	}

	public int fib(int n) {
		return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
	}

	// @RabbitHandler
	/*
	 * public void receive(String in, int receiver) throws InterruptedException
	 * { StopWatch watch = new StopWatch(); watch.start(); logger.info(receiver
	 * + "  Received '" + in + "'"); doWork(in); watch.stop();
	 * logger.info(receiver + " Done in " + watch.getTotalTimeSeconds() + "s");
	 * }
	 */

	/*
	 * private void doWork(String in) throws InterruptedException { for (char ch
	 * : in.toCharArray()) { if (ch == '.') { // 模拟复杂任务阻塞进程 Thread.sleep(1000);
	 * } } }
	 */

}
