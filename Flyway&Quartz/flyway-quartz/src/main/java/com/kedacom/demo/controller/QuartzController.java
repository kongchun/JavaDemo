
/**
 * @(#)QuartzController.java 2018年7月19日 Copyright 2018 it.kedacom.com, Inc. All
 *                           rights reserved.
 */

package com.kedacom.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kedacom.demo.service.QuartzService;

/**
 * 定时任务Controller
 * @author lihongguang
 * @date 2018年7月19日
 */
@Controller
public class QuartzController {

	@Autowired
	private QuartzService quartzService;

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("/start")
	public String start() {
		this.quartzService.startAll();
		return "redirect:index";
	}

	@RequestMapping("/add")
	public String add(Integer type, String jobName, String triggerName, String cron) {
		this.quartzService.addJob(type, jobName, triggerName, cron);
		return "redirect:index";
	}

	@RequestMapping("/del")
	public String del(Integer type, String jobName) {
		this.quartzService.deleteJob(type, jobName);
		return "redirect:index";
	}

	@RequestMapping("/pau")
	public String pau(Integer type, String jobName) {
		this.quartzService.pauseJob(type, jobName);
		return "redirect:index";
	}

	@RequestMapping("/res")
	public String res(Integer type, String jobName) {
		this.quartzService.resumeJob(type, jobName);
		return "redirect:index";
	}
}
