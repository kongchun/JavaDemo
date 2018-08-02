package com.kedacom.springamqp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedacom.springamqp.rmq.Client;

/**
 * 主服务接口实现
 * @author lihongguang
 * @date 2018年7月31日
 */
@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private Client sender;

	/**
	 * @see com.kedacom.springamqp.service.HomeService#send()
	 */

	@Override
	public void send() {
		sender.send();
	}

}
