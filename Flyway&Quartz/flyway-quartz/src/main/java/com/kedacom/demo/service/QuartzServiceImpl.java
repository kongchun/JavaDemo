
/**
 * @(#)QuartzServiceImpl.java 2018年7月19日 Copyright 2018 it.kedacom.com, Inc. All
 *                            rights reserved.
 */

package com.kedacom.demo.service;

import java.util.Random;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedacom.demo.config.QuartzOperation;
import com.kedacom.demo.job.FlywayJob;
import com.kedacom.demo.job.LoggerJob;

/**
 * Quartz服务实现
 * @author lihongguang
 * @date 2018年7月19日
 */
@Service
public class QuartzServiceImpl implements QuartzService {

	@Autowired
	private QuartzOperation quartzOperation;

	/**
	 * @see com.kedacom.demo.service.QuartzService#startAll()
	 */

	@Override
	public void startAll() {
		this.quartzOperation.doStart();
	}

	/**
	 * @see com.kedacom.demo.service.QuartzService#addJob(java.lang.Integer,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */

	@Override
	public void addJob(Integer type, String jobName, String triggerName, String cron) {
		JobDetail jobDetail = null;
		Trigger trigger = null;
		if (type == 1) {
			// jobDetail自带数据
			jobDetail = JobBuilder.newJob(LoggerJob.class).withIdentity(jobName, "group1")
					.usingJobData("jobSays", "Hello!").usingJobData("myFloatValue", new Random().nextFloat()).build();
			// 用CRON表达式
			// Seconds Minutes Hours Day-of-Month Month Day-of-Week Year
			// 不用CRON：.startAt(DateBuilder.futureDate(3, IntervalUnit.SECOND))
			// .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
			trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
		} else if (type == 2) {
			jobDetail = JobBuilder.newJob(FlywayJob.class).withIdentity(jobName, "group2").build();
			trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, "group2")
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
		}
		this.quartzOperation.doAdd(jobDetail, trigger);
	}

	/**
	 * @see com.kedacom.demo.service.QuartzService#deleteJob(java.lang.String)
	 */

	@Override
	public void deleteJob(Integer type, String jobName) {
		JobKey jobKey = null;
		if (type == 1) {
			jobKey = new JobKey(jobName, "group1");
		} else if (type == 2) {
			jobKey = new JobKey(jobName, "group2");
		}
		this.quartzOperation.doDelete(jobKey);
	}

	/**
	 * @see com.kedacom.demo.service.QuartzService#pauseJob(java.lang.String)
	 */

	@Override
	public void pauseJob(Integer type, String jobName) {
		JobKey jobKey = null;
		if (type == 1) {
			jobKey = new JobKey(jobName, "group1");
		} else if (type == 2) {
			jobKey = new JobKey(jobName, "group2");
		}
		this.quartzOperation.doPause(jobKey);
	}

	/**
	 * @see com.kedacom.demo.service.QuartzService#resumeJob(java.lang.String)
	 */

	@Override
	public void resumeJob(Integer type, String jobName) {
		JobKey jobKey = null;
		if (type == 1) {
			jobKey = new JobKey(jobName, "group1");
		} else if (type == 2) {
			jobKey = new JobKey(jobName, "group2");
		}
		this.quartzOperation.doResume(jobKey);
	}

}
