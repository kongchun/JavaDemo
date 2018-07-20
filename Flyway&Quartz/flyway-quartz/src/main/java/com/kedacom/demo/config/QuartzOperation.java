
/**
 * @(#)QuartzStart.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc. All
 *                      rights reserved.
 */

package com.kedacom.demo.config;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import com.kedacom.demo.listener.CustomTriggerListener;

/**
 * Quartz任务操作
 * @author lihongguang
 * @date 2018年7月18日
 */
@Component
public class QuartzOperation {

	public void doStart() {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void doAdd(JobDetail job, Trigger trigger) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.getListenerManager().addTriggerListener(new CustomTriggerListener(),
					GroupMatcher.anyTriggerGroup());
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void doDelete(JobKey jobKey) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void doPause(JobKey jobKey) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void doResume(JobKey jobKey) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			System.out.println(e.getMessage());
		}
	}
}
