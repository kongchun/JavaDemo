
/**
 * @(#)SpringInitializer.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc. All
 *                            rights reserved.
 */

package com.kedacom.demo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动Spring
 * @author lihongguang
 * @date 2018年7月18日
 */

public class SpringInitializer {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("applicationContext.xml");
	}

}
