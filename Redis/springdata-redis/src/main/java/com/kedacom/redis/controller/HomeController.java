package com.kedacom.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页
 * @author lihongguang
 * @date 2018年8月8日
 */
@Controller
public class HomeController {

	@RequestMapping("")
	public String home() {
		return "home";
	}
}
