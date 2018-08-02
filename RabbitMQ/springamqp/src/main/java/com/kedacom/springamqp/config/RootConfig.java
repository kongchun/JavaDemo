package com.kedacom.springamqp.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring ApplicationContext配置
 * @author lihongguang
 * @date 2018年7月30日
 */
@Configuration
@EnableRabbit
@ComponentScan("com.kedacom.springamqp")
public class RootConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
		return new CachingConnectionFactory("localhost");
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		return factory;
	}

	@Bean
	public TopicExchange topic() {
		return new TopicExchange("demo.topic.rpc");
	}

	@Bean
	public Queue queue() {
		return new Queue("demo.rpc.requests");
	}

	// 绑定队列到Exchange
	@Bean
	public Binding binding(TopicExchange exchange, Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with("rpc.#");
	}

	/*
	 * @Bean public Queue myQueue() { return new Queue("myqueue"); }
	 */

	/*
	 * @Bean public FanoutExchange fanout() { return new
	 * FanoutExchange("demo.fanout"); }
	 */

	/*
	 * @Bean public DirectExchange direct() { return new
	 * DirectExchange("demo.direct"); }
	 */

	/*
	 * @Bean public Queue autoDeleteQueue1() { return new AnonymousQueue(); }
	 * @Bean public Queue autoDeleteQueue2() { return new AnonymousQueue(); }
	 */

	/*
	 * @Bean public Binding binding1(FanoutExchange fanout, Queue
	 * autoDeleteQueue1) { return
	 * BindingBuilder.bind(autoDeleteQueue1).to(fanout); }
	 * @Bean public Binding binding2(FanoutExchange fanout, Queue
	 * autoDeleteQueue2) { return
	 * BindingBuilder.bind(autoDeleteQueue2).to(fanout); }
	 */
	/*
	 * @Bean public Binding binding1a(DirectExchange direct, Queue
	 * autoDeleteQueue1) { return
	 * BindingBuilder.bind(autoDeleteQueue1).to(direct).with("one"); }
	 * @Bean public Binding binding1b(DirectExchange direct, Queue
	 * autoDeleteQueue1) { return
	 * BindingBuilder.bind(autoDeleteQueue1).to(direct).with("two"); }
	 * @Bean public Binding binding2a(DirectExchange direct, Queue
	 * autoDeleteQueue2) { return
	 * BindingBuilder.bind(autoDeleteQueue2).to(direct).with("three"); }
	 * @Bean public Binding binding2b(DirectExchange direct, Queue
	 * autoDeleteQueue2) { return
	 * BindingBuilder.bind(autoDeleteQueue2).to(direct).with("two"); }
	 */
	/*
	 * @Bean public Binding binding1a(TopicExchange topic, Queue
	 * autoDeleteQueue1) { return
	 * BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.orange.*"); }
	 * @Bean public Binding binding1b(TopicExchange topic, Queue
	 * autoDeleteQueue1) { return
	 * BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.*.rabbit"); }
	 * @Bean public Binding binding2a(TopicExchange topic, Queue
	 * autoDeleteQueue2) { return
	 * BindingBuilder.bind(autoDeleteQueue2).to(topic).with("lazy.#"); }
	 */

	/*
	 * @Bean public Receiver receiver1() { return new Receiver(1); }
	 * @Bean public Receiver receiver2() { return new Receiver(2); }
	 */

}
