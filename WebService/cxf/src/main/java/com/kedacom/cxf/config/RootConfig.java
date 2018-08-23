
/**
 * @(#)RootConfig.java 2018年8月18日 Copyright 2018 it.kedacom.com, Inc. All rights
 *                     reserved.
 */

package com.kedacom.cxf.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.kedacom.cxf.service.UserService;

/**
 * (用一句话描述类的主要功能)
 * @author lihongguang
 * @date 2018年8月18日
 */

@Configuration
@ComponentScan("com.kedacom.cxf")
public class RootConfig {

	@Autowired
	UserService userService;

	@Bean
	public SpringBus cxf() {
		return new SpringBus();
	}

	@Bean
	JacksonJsonProvider jacksonJsonProvider() {
		return new JacksonJsonProvider();
	}

	@Bean
	public Server server(SpringBus cxf, JacksonJsonProvider jacksonJsonProvider) {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setBus(cxf);
		sf.setServiceBean(userService);
		sf.setAddress("/rest");
		sf.setProvider(jacksonJsonProvider);
		return sf.create();
	}
	/*
	 * @Autowired private HelloWorld helloWord;
	 * @Bean public Endpoint endpoint(SpringBus cxf) { EndpointImpl endpoint =
	 * new EndpointImpl(cxf, helloWord); endpoint.publish("/hello"); return
	 * endpoint; }
	 */
}
