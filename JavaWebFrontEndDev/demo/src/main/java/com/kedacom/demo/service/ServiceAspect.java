/**
 * 
 */
package com.kedacom.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP定义
 * @author lihongguang
 */
@Aspect
@Component
public class ServiceAspect {

	private static final Logger logger = LogManager.getLogger(ServiceAspect.class);

	/*
	 * 切入点定义： execution(modifiers-pattern? ret-type-pattern
	 * declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
	 */
	/**
	 * 保存操作切点
	 */
	@Pointcut("execution(* com.kedacom.demo.service.*.save*(..))")
	public void saveService() {
	}

	/**
	 * 修改操作切点
	 */
	@Pointcut("execution(* com.kedacom.demo.service.*.edit*(..))")
	public void editService() {
	}

	/**
	 * 保存之前
	 * @param point
	 */
	@Before("saveService()")
	public void beforeSave(JoinPoint point) {
		logger.info("Start saving user: " + point.getArgs()[0]);
	}

	/**
	 * 检查保存返回值
	 * @param point
	 * @param retVal
	 */
	@AfterReturning(pointcut = "saveService()", returning = "retVal")
	public void afterReturningSave(JoinPoint point, Object retVal) {
		if (retVal.equals(1)) {
			logger.info("Completed saving user: " + point.getArgs()[0]);
		} else {
			logger.info("Failed saving user: " + point.getArgs()[0]);
		}
	}

	@Before("editService()")
	public void beforeEdit(JoinPoint point) {
		logger.info("Start editing user: " + point.getArgs()[0]);
	}

	@AfterReturning(pointcut = "editService()", returning = "retVal")
	public void afterReturningEdit(JoinPoint point, Object retVal) {
		if (retVal.equals(1)) {
			logger.info("Completed editing user: " + point.getArgs()[0]);
		} else if (retVal.equals(-1)) {
			logger.info("Failed editing user: " + point.getArgs()[0] + " (Wrong Password)");
		} else {
			logger.info("Failed editing user: " + point.getArgs()[0]);
		}
	}
}
