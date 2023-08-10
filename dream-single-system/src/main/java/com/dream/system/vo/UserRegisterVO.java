package com.dream.system.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账号注册信息 DTO
 * 
 * @auther 飞花梦影
 * @date 2022-08-31 15:07:49
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Schema(description = "账号注册信息 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterVO implements Serializable {

	private static final long serialVersionUID = 6392081162841274361L;

	/**
	 * 用户名
	 */
	@Schema(description = "用户名")
	private String username;

	/**
	 * 手机号
	 */
	@Schema(description = "手机号")
	private String mobile;

	/**
	 * 密码
	 */
	@Schema(description = "密码")
	private String password;
}