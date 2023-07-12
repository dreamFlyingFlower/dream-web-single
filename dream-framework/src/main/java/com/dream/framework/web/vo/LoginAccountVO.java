package com.dream.framework.web.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户登录信息 DTO
 * 
 * @auther 飞花梦影
 * @date 2022-08-31 14:54:58
 */
@ApiModel(description = "账户登录信息 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAccountVO implements Serializable {

	private static final long serialVersionUID = -2132173723754933687L;

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

	/**
	 * 域:c-c端用户;b-b端用户
	 */
	@ApiModelProperty("域:c-c端用户;b-b端用户")
	private String domain;
}