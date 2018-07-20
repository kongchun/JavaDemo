
/**
 * @(#)QuartzService.java 2018年7月19日 Copyright 2018 it.kedacom.com, Inc. All
 *                        rights reserved.
 */

package com.kedacom.demo.service;

/**
 * Quartz服务接口
 * @author lihongguang
 * @date 2018年7月19日
 */

public interface QuartzService {

	void startAll();

	void addJob(Integer type, String jobName, String triggerName, String cron);

	void deleteJob(Integer type, String jobName);

	void pauseJob(Integer type, String jobName);

	void resumeJob(Integer type, String jobName);

}
