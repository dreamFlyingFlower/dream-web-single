package com.dream.web.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "账号注册信息 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterVO implements Serializable {

	private static final long serialVersionUID = 6392081162841274361L;

	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String username;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	private String password;
}