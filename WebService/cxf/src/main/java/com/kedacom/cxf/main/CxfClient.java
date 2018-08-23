
/**
 * @(#)CxfClient.java 2018年8月18日 Copyright 2018 it.kedacom.com, Inc. All rights
 *                    reserved.
 */

package com.kedacom.cxf.main;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kedacom.cxf.service.HelloWorld;

/**
 * (用一句话描述类的主要功能)
 * @author lihongguang
 * @date 2018年8月18日
 */

public class CxfClient {

	/**
	 * (用一句话描述方法的主要功能)
	 * @param args
	 */

	private static final Logger logger = LoggerFactory.getLogger(CxfClient.class);

	public static void main(String[] args) {

		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress("http://localhost:8080/cxf/services/hello?wsdl");
		jaxWsProxyFactoryBean.setServiceClass(HelloWorld.class);
		HelloWorld helloWorld = (HelloWorld) jaxWsProxyFactoryBean.create();
		logger.info(helloWorld.sayHi(System.getProperty("user.name")));
	}

}
