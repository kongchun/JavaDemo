package com.kedacom.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedacom.redis.service.HomeService;

/**
 * 主页
 * @author lihongguang
 * @date 2018年8月8日
 */
@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;

	@RequestMapping("")
	public String home() {
		return "home";
	}

	@RequestMapping("/exists")
	@ResponseBody
	public Boolean exists(String key) {
		return homeService.exists(key);
	}

	@RequestMapping("/del")
	@ResponseBody
	public Boolean del(String key) {
		return homeService.del(key);
	}

	@RequestMapping("/expire")
	@ResponseBody
	public Boolean expire(String key, Long timeout) {
		return homeService.expire(key, timeout);
	}

	@RequestMapping("/ttl")
	@ResponseBody
	public Long ttl(String key) {
		return homeService.ttl(key);
	}

	@RequestMapping("/set")
	@ResponseBody
	public Boolean set(String key, String value) {
		homeService.set(key, value);
		return true;
	}

	@RequestMapping("/get")
	@ResponseBody
	public String get(String key) {
		return homeService.get(key);
	}

	@RequestMapping("/incr")
	@ResponseBody
	public Long incr(String key, Long delta) {
		return homeService.incrDecr(key, delta);
	}

	@RequestMapping("/decr")
	@ResponseBody
	public Long decr(String key, Long delta) {
		return homeService.incrDecr(key, -delta);
	}
}
