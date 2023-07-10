package com.dream.web.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.dream.web.entity.OperateLog;
import com.wy.logger.Logger;

/**
 * 异步保存日志
 *
 * @author 飞花梦影
 * @date 2022-11-12 21:17:38
 * @git {@link https://github.com/dreamFlyingFlower }
 */
public interface OperateLogService {

	/**
	 * 环绕日志
	 * 
	 * @param joinPoint 切入点
	 * @param logger 注解日志
	 */
	Object doAroundLogger(ProceedingJoinPoint joinPoint, Logger logger);

	/**
	 * 环绕日志
	 * 
	 * @param joinPoint 切入点
	 * @param logger 注解日志
	 */
	Object doAroundController(ProceedingJoinPoint joinPoint);

	/**
	 * 拦截异常操作
	 * 
	 * @param joinPoint 切入点
	 * @param logger 日志注解
	 * @param e 异常
	 */
	void doAfterThrowingLogger(final JoinPoint joinPoint, Logger logger, Exception e);

	/**
	 * 保存系统日志记录
	 */
	void saveOperateLog(OperateLog operateLog);
}