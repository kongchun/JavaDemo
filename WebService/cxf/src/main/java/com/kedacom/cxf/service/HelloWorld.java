
/**
 * @(#)HelloWorld.java 2018年8月18日 Copyright 2018 it.kedacom.com, Inc. All rights
 *                     reserved.
 */

package com.kedacom.cxf.service;

import javax.jws.WebService;

/**
 * (用一句话描述类的主要功能)
 * @author lihongguang
 * @date 2018年8月18日
 */
@WebService
public interface HelloWorld {

	String sayHi(String text);
}
