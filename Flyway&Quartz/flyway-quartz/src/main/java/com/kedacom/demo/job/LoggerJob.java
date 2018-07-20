
/**
 * @(#)LoggerJob.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc. All rights
 *                    reserved.
 */

package com.kedacom.demo.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志定时任务
 * @author lihongguang
 * @date 2018年7月18日
 */

public class LoggerJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(LoggerJob.class);

	/**
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		JobDetail jobDetail = context.getJobDetail();
		Trigger trigger = context.getTrigger();

		JobKey key = jobDetail.getKey();
		JobDataMap dataMap = jobDetail.getJobDataMap();

		Date startTime = trigger.getStartTime();

		String jobSays = dataMap.getString("jobSays");
		float myFloatValue = dataMap.getFloat("myFloatValue");

		logger.info("于" + startTime + "开始执行的任务实例：" + key + "，自带消息：" + jobSays + "和" + myFloatValue);
	}

}
