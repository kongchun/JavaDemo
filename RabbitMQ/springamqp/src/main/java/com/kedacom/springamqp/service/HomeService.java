package com.kedacom.springamqp.service;

/**
 * 主服务接口
 * @author lihongguang
 * @date 2018年7月31日
 */

public interface HomeService {

	/**
	 * 发送消息
	 */
	Integer send(Integer index);
}
