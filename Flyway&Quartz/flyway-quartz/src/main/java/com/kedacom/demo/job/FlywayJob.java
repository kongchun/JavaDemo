
/**
 * @(#)FlywayJob.java 2018年7月19日 Copyright 2018 it.kedacom.com, Inc. All rights
 *                    reserved.
 */

package com.kedacom.demo.job;

import javax.inject.Inject;
import javax.inject.Named;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.kedacom.demo.config.FlywayOperation;

/**
 * Flyway定时任务
 * @author lihongguang
 * @date 2018年7月19日
 */
@Named
public class FlywayJob implements Job {

	@Inject
	private FlywayOperation flywayOperation;

	/**
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		this.flywayOperation.doMigrate();
	}

}
