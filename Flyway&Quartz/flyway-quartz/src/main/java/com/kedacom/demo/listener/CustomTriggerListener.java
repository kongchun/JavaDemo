
/**
 * @(#)CustomTriggerListener.java 2018年7月18日 Copyright 2018 it.kedacom.com, Inc.
 *                                All rights reserved.
 */

package com.kedacom.demo.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务触发器的监听器
 * @author lihongguang
 * @date 2018年7月18日
 */

public class CustomTriggerListener implements TriggerListener {

	private static Logger logger = LoggerFactory.getLogger(CustomTriggerListener.class);

	/**
	 * @see org.quartz.TriggerListener#getName()
	 */

	@Override
	public String getName() {
		return "CustomTriggerListener";
	}

	/**
	 * @see org.quartz.TriggerListener#triggerFired(org.quartz.Trigger,
	 *      org.quartz.JobExecutionContext)
	 */

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		logger.info(trigger.getKey() + "触发器被激发！");
	}

	/**
	 * @see org.quartz.TriggerListener#vetoJobExecution(org.quartz.Trigger,
	 *      org.quartz.JobExecutionContext)
	 */

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		return false;
	}

	/**
	 * @see org.quartz.TriggerListener#triggerMisfired(org.quartz.Trigger)
	 */

	@Override
	public void triggerMisfired(Trigger trigger) {
		logger.warn(trigger.getKey() + "触发器错误激发！");
	}

	/**
	 * @see org.quartz.TriggerListener#triggerComplete(org.quartz.Trigger,
	 *      org.quartz.JobExecutionContext,
	 *      org.quartz.Trigger.CompletedExecutionInstruction)
	 */

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		logger.info(trigger.getKey() + "触发器完成！");
	}

}
