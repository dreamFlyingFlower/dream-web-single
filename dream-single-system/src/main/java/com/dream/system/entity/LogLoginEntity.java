package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 登录日志
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "登录日志")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_log_login")
public class LogLoginEntity extends AbstractEntity {

	private static final long serialVersionUID = 6218564322488649681L;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 登录IP
	 */
	private String ip;

	/**
	 * 登录地点
	 */
	private String address;

	/**
	 * User Agent
	 */
	private String userAgent;

	/**
	 * 登录状态 0：失败 1：成功
	 */
	private Integer status;

	/**
	 * 操作信息 0：登录成功 1：退出成功 2：验证码错误 3：账号密码错误
	 */
	private Integer operation;
}