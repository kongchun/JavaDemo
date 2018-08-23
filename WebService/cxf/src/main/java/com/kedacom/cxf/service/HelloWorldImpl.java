
/**
 * @(#)HelloWorldImpl.java 2018年8月18日 Copyright 2018 it.kedacom.com, Inc. All
 *                         rights reserved.
 */

package com.kedacom.cxf.service;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

/**
 * (用一句话描述类的主要功能)
 * @author lihongguang
 * @date 2018年8月18日
 */
@Service
@WebService(endpointInterface = "com.kedacom.cxf.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	/**
	 * s
	 * @see com.kedacom.cxf.service.HelloWorld#sayHi(java.lang.String)
	 */

	@Override
	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}

}
