
/**
 * @(#)QuartzTest.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc. All rights
 *                     reserved.
 */

package com.kedacom.demo.test;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import com.kedacom.demo.job.LoggerJob;
import com.kedacom.demo.listener.CustomTriggerListener;

/**
 * 手动控制Quartz
 * @author lihongguang
 * @date 2018年7月18日
 */

public class QuartzTest {

	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// scheduler.addCalendar("myCal", cal, false, false);

			JobDetail job = JobBuilder.newJob(LoggerJob.class).withIdentity("logger_job1", "group1")
					.usingJobData("jobSays", "Hello World!").usingJobData("myFloatValue", 3.141f).build();

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
					// .startAt(DateBuilder.futureDate(3, IntervalUnit.SECOND))
					// .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
					// Seconds Minutes Hours Day-of-Month Month Day-of-Week Year
					.withSchedule(CronScheduleBuilder.cronSchedule("3/5 * * * * ? *"))
					// .modifiedByCalendar("myCal")
					.build();

			scheduler.getListenerManager().addTriggerListener(new CustomTriggerListener(),
					GroupMatcher.triggerGroupEquals("group1"));

			scheduler.scheduleJob(job, trigger);

			scheduler.start();

		} catch (SchedulerException e) {
			System.out.println(e.getMessage());
		}
	}

}
