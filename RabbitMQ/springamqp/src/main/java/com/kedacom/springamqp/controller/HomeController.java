package com.kedacom.springamqp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedacom.springamqp.service.HomeService;

/**
 * 主页Controller
 * @author lihongguang
 * @date 2018年7月30日
 */
@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;

	@RequestMapping("")
	public String home() {
		return "home";
	}

	@RequestMapping("/send")
	@ResponseBody
	public Integer send(Integer index) {
		return homeService.send(index);
	}
}
